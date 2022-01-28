package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import model.RentalAgreement;
import model.Tool;
import model.ToolPriceGuide;

public class ToolRentalController {

	public static void main(String[] args) {
		Tool myTool = Tool.newToolInstance(args[0]);
		int rentalDayCount = Integer.valueOf(args[1]);
		LocalDate checkoutDate = LocalDate.parse(args[3], DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		Double discount = Double.parseDouble(args[2]);
		
		ToolPriceGuide.pricingGuideNewInstance(myTool.getToolCode());
		RentalAgreement rentalAgreement = RentalAgreementGenerator.generateRentalAgreement(
				myTool.getToolCode(), rentalDayCount,discount ,checkoutDate );
		
		System.out.print(rentalAgreement.getChargeDaysCount());
		rentalAgreement.printRentalAgreement();
	}

}
