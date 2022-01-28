package controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import model.RentalAgreement;
import model.ToolPriceGuide;

public class RentalAgreementGenerator {
	
	private static ToolPriceGuide priceGuide;
	private static RentalAgreement rentalAgreement = new RentalAgreement();
	
	public static RentalAgreement generateRentalAgreement(String toolCode, int rentalDayCount, 
			Double discountPercentage, LocalDate checkoutDate) {
	
		priceGuide = ToolPriceGuide.pricingGuideNewInstance(toolCode);
		rentalAgreement.setCustomerTool(toolCode);
		rentalAgreement.setRentalDayCount(rentalDayCount);
		rentalAgreement.setCheckoutDate(checkoutDate);
		rentalAgreement.setDueDate(checkoutDate.plusDays(rentalDayCount));	
		setPricingFields(toolCode);
		return rentalAgreement;
	}
	
	private static void setPricingFields(String toolCode) {	
		rentalAgreement.setDailyRentalCharge(priceGuide.getDailyCharge());
		int chargeDaysCount = calculateChargeDaysCount(rentalAgreement.getCheckoutDate(), 
				rentalAgreement.getRentalDayCount(), priceGuide.isWeekdayCharge(),
				priceGuide.isWeekendCharge(), priceGuide.isHolidayCharge());
		rentalAgreement.setChargeDaysCount(chargeDaysCount);
		rentalAgreement.setPrediscountCharge(priceGuide.getDailyCharge() * chargeDaysCount);
		//RentalAgreement.setDiscountPercentage();
		//RentalAgreement.setDiscountAmount();
		//RentalAgreement.setFinalCharge();
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

}
