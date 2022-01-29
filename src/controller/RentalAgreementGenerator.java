package controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import model.RentalAgreement;
import model.ToolPriceGuide;

public class RentalAgreementGenerator {
	
	private static ToolPriceGuide priceGuide;
	private static RentalAgreement rentalAgreement = new RentalAgreement();
	
	public static RentalAgreement generateRentalAgreement(String toolCode, int rentalDayCount, 
			int discountPercentage, LocalDate checkoutDate) {
	
		priceGuide = ToolPriceGuide.pricingGuideNewInstance(toolCode);
		rentalAgreement.setCustomerTool(toolCode);
		rentalAgreement.setRentalDayCount(rentalDayCount);
		rentalAgreement.setCheckoutDate(checkoutDate);
		rentalAgreement.setDueDate(checkoutDate.plusDays(rentalDayCount));	
		rentalAgreement.setDiscountPercentage(discountPercentage);
		setPricingFields(toolCode, discountPercentage);
		return rentalAgreement;
	}
	
	private static void setPricingFields(String toolCode, int discountPercentage) {	
		rentalAgreement.setDailyRentalCharge(priceGuide.getDailyCharge());
		int chargeDaysCount = calculateChargeDaysCount(rentalAgreement.getCheckoutDate(), 
				rentalAgreement.getRentalDayCount(), priceGuide.isWeekdayCharge(),
				priceGuide.isWeekendCharge(), priceGuide.isHolidayCharge());
		rentalAgreement.setChargeDaysCount(chargeDaysCount);
		Double prediscountCharge = priceGuide.getDailyCharge() * chargeDaysCount;
		rentalAgreement.setPrediscountCharge(prediscountCharge);	
		Double discountAmount = calculateDiscountAmount(priceGuide.getDailyCharge(), 
				discountPercentage, chargeDaysCount);
		rentalAgreement.setDiscountAmount(discountAmount);
		Double finalCharge = new BigDecimal(prediscountCharge - discountAmount)
				.setScale(2, RoundingMode.HALF_UP).doubleValue();
		rentalAgreement.setFinalCharge(finalCharge);
	}
	
	private static int calculateChargeDaysCount(LocalDate checkoutDate, 
			int rentalDayCount, Boolean weekdayCharged, 
			Boolean weekendCharged, Boolean holidayCharged) {
		checkoutDate = checkoutDate.plusDays(1);
		if(rentalDayCount > 0) {
			int chargeDaysCount = 0;
			DayOfWeek dayOfWeek = checkoutDate.getDayOfWeek();
			if(weekdayCharged && dayOfWeek.getValue() <= 5) {
				chargeDaysCount++;
			}
			if(weekendCharged && dayOfWeek.getValue()>= 6) {
				chargeDaysCount++;
			}
			if(holidayCharged && isHoliday(checkoutDate)) {
				chargeDaysCount++;
			}
			rentalDayCount--;
			chargeDaysCount += calculateChargeDaysCount(checkoutDate, rentalDayCount, weekdayCharged, weekendCharged, holidayCharged);
			return chargeDaysCount;	
		}
		rentalAgreement.setDueDate(checkoutDate);
		return 0;
	}
	
	private static boolean isHoliday(LocalDate checkoutDate) {	
		if(checkoutDate.getMonth().equals(Month.JULY) && checkoutDate.getDayOfMonth() == 4) {
			return true;
		}else if(checkoutDate.getMonth().equals(Month.SEPTEMBER) 
				&& checkoutDate.getDayOfMonth() < 7 
				&& checkoutDate.getDayOfWeek().equals(DayOfWeek.MONDAY)){
			return true;
		}
		return false;
	}
	
	private static Double calculateDiscountAmount(Double dailyCharge, 
			int discountPercentage, int chargeDaysCount) {
		Double discountAmount;
		discountAmount = new BigDecimal(dailyCharge * (discountPercentage/100d)).doubleValue();
		discountAmount = new BigDecimal(discountAmount * Double.valueOf(chargeDaysCount)).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return discountAmount;
		
	}

}
