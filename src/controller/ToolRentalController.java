package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Scanner;

import model.RentalAgreement;
import model.Tool;

public class ToolRentalController {

	public static void main(String[] args) {
		RentalAgreement rentalAgreement;
		if(args !=null && args.length == 4) {
			Tool myTool = Tool.newToolInstance(args[0]);
			int rentalDayCount = Integer.valueOf(args[1]);
			int discount = Integer.parseInt(args[2]);
			LocalDate checkoutDate;
			try {
				if(discount < 0 || discount >100) {
					throw new UserInputException("Please select a discount percentage between 0 and 100."); 
				}
				if(rentalDayCount < 1) {
					throw new UserInputException("Please specify a number of rental days that is at least 1.");
				}
				checkoutDate = LocalDate.parse(args[3], DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
				rentalAgreement = RentalAgreementGenerator.generateRentalAgreement(
						myTool.getToolCode(), rentalDayCount,discount ,checkoutDate);
			}
			catch(UserInputException e){
				System.out.println(e.getMessage());
				return;
			}catch(DateTimeParseException e) {
				System.out.println(e.getMessage());
				return;
			}
		}else {
			Scanner s = new Scanner(System.in);
			
			System.out.println("Please enter your tool code: ");
			Tool myTool = Tool.newToolInstance(s.next());
			
			System.out.println("Please enter your rental day count: ");
			int rentalDayCount = s.nextInt();
			
			System.out.println("Please enter your discount percentage: ");
			int discount = s.nextInt();
			
			System.out.println("Please enter you checkout date in the format \"mm/dd/yy:\"");
			LocalDate checkoutDate = LocalDate.parse(s.next(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
			rentalAgreement = RentalAgreementGenerator.generateRentalAgreement(
					myTool.getToolCode(), rentalDayCount,discount ,checkoutDate);
		}
		
		rentalAgreement.printRentalAgreement();
	}

}

class UserInputException extends Exception{  
    public UserInputException(String str)  
    {   
        super(str);  
    }  
}  
