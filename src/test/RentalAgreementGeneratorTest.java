package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.ToolRentalController;

public class RentalAgreementGeneratorTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void resetString() {	
		outContent.reset();
	}

	@Test
	public void testInvalidDiscount() {
		String[] args = new String[] {"JAKR", "5", "101", "9/3/15"};
		ToolRentalController.main(args);

		assertEquals("Please select a discount percentage between 0 and 100.\n", outContent.toString());
	}
	
	@Test 
	public void testIndependenceDaySaturday() {
		String[] args = new String[] {"LADW", "3", "10", "7/2/20"};
		ToolRentalController.main(args);
		assertEquals("Tool code: LADW\n"
				+ "Tool type: Ladder\n"
				+ "Tool brand: Werner\n"
				+ "Rental days: 3\n"
				+ "Check out date: 7/2/20\n"
				+ "Due date: 7/6/20\n"
				+ "Daily rental charge: $1.99\n"
				+ "Charge days: 2\n"
				+ "Pre-discount charge: $3.98\n"
				+ "Discount percent: 10%\n"
				+ "Discount amount: $0.40\n"
				+ "Final charge: $3.58\n"
				, outContent.toString());
		
	}
	
	@Test 
	public void testChainsawRentedWhenIndependenceDaySaturday() {
		String[] args = new String[] {"CHNS", "5", "25", "7/2/15"};
		ToolRentalController.main(args);
		assertEquals("Tool code: CHNS\n"
				+ "Tool type: Chainsaw\n"
				+ "Tool brand: Stihl\n"
				+ "Rental days: 5\n"
				+ "Check out date: 7/2/15\n"
				+ "Due date: 7/8/15\n"
				+ "Daily rental charge: $1.49\n"
				+ "Charge days: 3\n"
				+ "Pre-discount charge: $4.47\n"
				+ "Discount percent: 25%\n"
				+ "Discount amount: $1.12\n"
				+ "Final charge: $3.35\n"
				, outContent.toString());
	}
	
	@Test 
	public void testDeWaltJackHammerLaborDay() {
		String[] args = new String[] {"JAKD", "6", "0", "9/3/15"};
		ToolRentalController.main(args);
		assertEquals("Tool code: JAKD\n"
				+ "Tool type: Jackhammer\n"
				+ "Tool brand: DeWalt\n"
				+ "Rental days: 6\n"
				+ "Check out date: 9/3/15\n"
				+ "Due date: 9/10/15\n"
				+ "Daily rental charge: $2.99\n"
				+ "Charge days: 3\n"
				+ "Pre-discount charge: $8.97\n"
				+ "Discount percent: 0%\n"
				+ "Discount amount: $0.00\n"
				+ "Final charge: $8.97\n"
				, outContent.toString());
	}	
	
	@Test 
	public void testRidgidJackHammerIndependanceDay() {
		String[] args = new String[] {"JAKR", "9", "0", "7/2/15"};
		ToolRentalController.main(args);
		assertEquals("Tool code: JAKR\n"
				+ "Tool type: Jackhammer\n"
				+ "Tool brand: Ridgid\n"
				+ "Rental days: 9\n"
				+ "Check out date: 7/2/15\n"
				+ "Due date: 7/12/15\n"
				+ "Daily rental charge: $2.99\n"
				+ "Charge days: 5\n"
				+ "Pre-discount charge: $14.95\n"
				+ "Discount percent: 0%\n"
				+ "Discount amount: $0.00\n"
				+ "Final charge: $14.95\n"
				, outContent.toString());
	}	
	
	@Test 
	public void testRidgidJackHammerIndependanceDay2020() {
		String[] args = new String[] {"JAKR", "4", "50", "7/2/20"};
		ToolRentalController.main(args);
		assertEquals("Tool code: JAKR\n"
				+ "Tool type: Jackhammer\n"
				+ "Tool brand: Ridgid\n"
				+ "Rental days: 4\n"
				+ "Check out date: 7/2/20\n"
				+ "Due date: 7/7/20\n"
				+ "Daily rental charge: $2.99\n"
				+ "Charge days: 1\n"
				+ "Pre-discount charge: $2.99\n"
				+ "Discount percent: 50%\n"
				+ "Discount amount: $1.50\n"
				+ "Final charge: $1.49\n"
				, outContent.toString());
	}
}
