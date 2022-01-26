package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToolPriceGuide {
	
	private static String toolType;
	private static Double dailyCharge;
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
	public ToolPriceGuide(String requestedToolCode) {
		new Tool(requestedToolCode);
		String requestedToolType = Tool.getToolType();
		ToolPriceGuide.setToolType(requestedToolType);
		for(JsonNode toolPricing : pricingCatalog.get("pricing")) {
			if(toolPricing.get("toolType").asText().equals(requestedToolType)) {
				ToolPriceGuide.setToolType(toolPricing.get("toolType").asText());
				ToolPriceGuide.setDailyCharge(toolPricing.get("dailyCharge").doubleValue());
				ToolPriceGuide.setWeekendCharge(toolPricing.get("weekendCharge").asBoolean());
				ToolPriceGuide.setWeekdayCharge(toolPricing.get("weekdayCharge").asBoolean());
				ToolPriceGuide.setHolidayCharge(toolPricing.get("holidayCharge").asBoolean());	
			}
		}
	};
	
	
	public static String getToolType() {
		return toolType;
	}
	
	private static void setToolType(String toolType) {
		ToolPriceGuide.toolType = toolType;
	}
	
	public static Double getDailyCharge() {
		return dailyCharge;
	}
	
	private static void setDailyCharge(Double dailyCharge) {	
		ToolPriceGuide.dailyCharge = dailyCharge;
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
