package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToolPriceGuide {
	
	private static String toolType;
	private static BigDecimal dailyCharge;
	private static boolean weekdayCharge;
	private static boolean weekendCharge;
	private static boolean holidayCharge;
	private static JsonNode pricingCatalog = initCatalog();
	
	private static JsonNode initCatalog() {
		//read in json parse in 
		ObjectMapper objMapper = new ObjectMapper();
		try {
			JsonNode catalog = objMapper.readTree(Files.readAllBytes(Paths.get("src/model/pricingCatalog.json")));
			return catalog;
		}catch(IOException ioExeption){
			System.out.print("An error occured parsing the pricing catalog");
			return null;
		}
	}
	private ToolPriceGuide() {};
	public ToolPriceGuide(String requestedToolType) {
		ToolPriceGuide.setToolType(requestedToolType);
		for(JsonNode toolPricing : pricingCatalog.get("pricing")) {
			if(toolPricing.get("toolType").asText().equals(requestedToolType)) {
				ToolPriceGuide.setToolType(toolPricing.get("toolType").asText());
				ToolPriceGuide.setDailyCharge(new BigDecimal(toolPricing.get("toolType").asDouble()));
				ToolPriceGuide.setWeekdayCharge(toolPricing.get("weekdayCharge").asBoolean());
				
			}
		}
	};
	
	
	public static String getToolType() {
		return toolType;
	}
	
	private static void setToolType(String toolType) {
		ToolPriceGuide.toolType = toolType;
	}
	
	public static BigDecimal getDailyCharge() {
		return dailyCharge;
	}
	
	private static void setDailyCharge(BigDecimal brand) {
		ToolPriceGuide.dailyCharge = brand;
	}
	public static boolean isWeekdayCharge() {
		return weekdayCharge;
	}
	public static void setWeekdayCharge(boolean weekdayCharge) {
		ToolPriceGuide.weekdayCharge = weekdayCharge;
	}
	public static boolean isWeekendCharge() {
		return weekendCharge;
	}
	public static void setWeekendCharge(boolean weekendCharge) {
		ToolPriceGuide.weekendCharge = weekendCharge;
	}
	public static boolean isHolidayCharge() {
		return holidayCharge;
	}
	public static void setHolidayCharge(boolean holidayCharge) {
		ToolPriceGuide.holidayCharge = holidayCharge;
	}
}
