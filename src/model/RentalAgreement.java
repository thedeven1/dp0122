package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class RentalAgreement {
	private Tool customerTool;
	private int rentalDayCount;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private Double dailyRentalCharge;
	private int chargeDaysCount;
	private Double prediscountCharge;
	private int discountPercentage;
	private Double discountAmount;
	private Double finalCharge;
	
	public void printRentalAgreement() {
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("Tool code: " + this.customerTool.getToolCode());
		System.out.println("Tool type: " + this.customerTool.getToolType());
		System.out.println("Tool brand: " + this.customerTool.getBrand());
		System.out.println("Rental days: " + this.getRentalDayCount());
		System.out.println("Check out date: " 
				+ this.getCheckoutDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
		System.out.println("Due date: " 
					+ this.getDueDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
		System.out.println("Daily rental charge: $" + df.format(this.getDailyRentalCharge()));
		System.out.println("Charge days: " +  this.getChargeDaysCount());
		System.out.println("Pre-discount charge: $" + df.format(this.getPrediscountCharge()));
		System.out.println("Discount percent: " + this.getDiscountPercentage()+"%");
		System.out.println("Discount amount: $" + df.format(this.getDiscountAmount()));
		System.out.println("Final charge: $" + df.format(this.getFinalCharge()));

	}

	public Tool getCustomerTool() {
		return customerTool;
	}
	public void setCustomerTool(String toolCode) {
		this.customerTool = new Tool(toolCode);
	}
	public int getRentalDayCount() {
		return rentalDayCount;
	}
	public void setRentalDayCount(int rentalDayCount) {
		this.rentalDayCount = rentalDayCount;
	}
	public  LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public Double getDailyRentalCharge() {
		return dailyRentalCharge;
	}
	public void setDailyRentalCharge(Double dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}
	public int getChargeDaysCount() {
		return chargeDaysCount;
	}
	public void setChargeDaysCount(int chargeDaysCount) {
		this.chargeDaysCount = chargeDaysCount;
	}
	public Double getPrediscountCharge() {
		return prediscountCharge;
	}
	public void setPrediscountCharge(Double prediscountCharge) {
		this.prediscountCharge = prediscountCharge;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(int discountPercentage) {		
			this.discountPercentage = discountPercentage;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getFinalCharge() {
		return finalCharge;
	}
	public void setFinalCharge(Double finalCharge) {
		this.finalCharge = finalCharge;
	}
	
}
