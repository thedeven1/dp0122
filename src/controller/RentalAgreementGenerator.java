package controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import model.RentalAgreement;
import model.ToolPriceGuide;

public class RentalAgreementGenerator {
	
	public static void generateRentalAgreement(String toolCode, int rentalDayCount, 
			BigDecimal discountPercentage, LocalDate checkoutDate) {
	
		RentalAgreement.setCustomerTool(toolCode);
		RentalAgreement.setRentalDayCount(rentalDayCount);
		RentalAgreement.setCheckoutDate(checkoutDate);
		RentalAgreement.setDueDate(checkoutDate.plusDays(rentalDayCount));
		//RentalAgreement.setDailyRentalCharge();
		//RentalAgreement.setChargeDaysCount();
		//RentalAgreement.setPrediscountCharge();
		//RentalAgreement.setDiscountPercentage();
		//RentalAgreement.setDiscountAmount();
		//RentalAgreement.setFinalCharge();
		
	}
	
	public static void printRentalAgreement() {
		
	}
	
	public static void setPricingFields(String toolCode) {
		new ToolPriceGuide(toolCode);
	}

}
