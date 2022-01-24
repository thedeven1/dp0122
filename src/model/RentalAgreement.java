package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {
	private static Tool customerTool;
	private static int rentalDayCount;
	private static LocalDate checkoutDate;
	private static LocalDate dueDate;
	private static BigDecimal dailyRentalCharge;
	private static int chargeDaysCount;
	private static BigDecimal prediscountCharge;
	private static int discountPercentage;
	private static BigDecimal discountAmount;
	private static BigDecimal finalCharge;
	
	public static Tool getCustomerTool() {
		return customerTool;
	}
	public static void setCustomerTool(String toolCode) {
		RentalAgreement.customerTool = new Tool(toolCode);
	}
	public static int getRentalDayCount() {
		return rentalDayCount;
	}
	public static void setRentalDayCount(int rentalDayCount) {
		RentalAgreement.rentalDayCount = rentalDayCount;
	}
	public static LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public static void setCheckoutDate(LocalDate checkoutDate) {
		RentalAgreement.checkoutDate = checkoutDate;
	}
	public static LocalDate getDueDate() {
		return dueDate;
	}
	public static void setDueDate(LocalDate dueDate) {
		RentalAgreement.dueDate = dueDate;
	}
	public static BigDecimal getDailyRentalCharge() {
		return dailyRentalCharge;
	}
	public static void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
		RentalAgreement.dailyRentalCharge = dailyRentalCharge;
	}
	public static int getChargeDaysCount() {
		return chargeDaysCount;
	}
	public static void setChargeDaysCount(int chargeDaysCount) {
		RentalAgreement.chargeDaysCount = chargeDaysCount;
	}
	public static BigDecimal getPrediscountCharge() {
		return prediscountCharge;
	}
	public static void setPrediscountCharge(BigDecimal prediscountCharge) {
		RentalAgreement.prediscountCharge = prediscountCharge;
	}
	public static int getDiscountPercentage() {
		return discountPercentage;
	}
	public static void setDiscountPercentage(int discountPercentage) {
		RentalAgreement.discountPercentage = discountPercentage;
	}
	public static BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public static void setDiscountAmount(BigDecimal discountAmount) {
		RentalAgreement.discountAmount = discountAmount;
	}
	public static BigDecimal getFinalCharge() {
		return finalCharge;
	}
	public static void setFinalCharge(BigDecimal finalCharge) {
		RentalAgreement.finalCharge = finalCharge;
	}
	
}
