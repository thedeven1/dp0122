package controller;

import model.Tool;

public class ToolRentalController {

	public static void main(String[] args) {
		Tool myTool = new Tool(args[0]);
		
		//RentalAgreement yourRentalAgreement = new RentalAgreement(myTool, rentalDayCount, dicountPercentage, checkoutDate);
		System.out.println("tool code: " + myTool.getToolCode());

		System.out.println("tool type: " + myTool.getToolType());

		System.out.println("tool brand: " + myTool.getBrand());
	}

}
