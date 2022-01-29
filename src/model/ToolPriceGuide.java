package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToolPriceGuide {
	
	private String toolType;
	private Double dailyCharge;
	private boolean weekdayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
	private JsonNode pricingCatalog = initCatalog();
	
	private static JsonNode initCatalog() {
		ObjectMapper objMapper = new ObjectMapper();
		try {
			JsonNode catalog = objMapper.readTree(Files.readAllBytes(Paths.get("src/model/pricingCatalog.json")));
			return catalog;
		}catch(IOException ioExeption){
			System.out.print("An error occured parsing the pricing catalog");
			return null;
		}
	}
	
	public ToolPriceGuide(String requestedToolCode) {
		Tool requestedTool = new Tool(requestedToolCode);
		String requestedToolType = requestedTool.getToolType();
		
		this.setToolType(requestedToolType);
		for(JsonNode toolPricing : pricingCatalog.get("pricing")) {
			if(toolPricing.get("toolType").asText().equals(requestedToolType)) {
				this.setToolType(toolPricing.get("toolType").asText());
				this.setDailyCharge(toolPricing.get("dailyCharge").doubleValue());
				this.setWeekendCharge(toolPricing.get("weekendCharge").asBoolean());
				this.setWeekdayCharge(toolPricing.get("weekdayCharge").asBoolean());
				this.setHolidayCharge(toolPricing.get("holidayCharge").asBoolean());	
			}
		}
	};

	public String getToolType() {
		return toolType;
	}

	public Double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(Double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}

	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}

	public boolean isWeekendCharge() {
		return weekendCharge;
	}

	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}

	public boolean isHolidayCharge() {
		return holidayCharge;
	}

	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}

	public JsonNode getPricingCatalog() {
		return pricingCatalog;
	}

	public void setPricingCatalog(JsonNode pricingCatalog) {
		this.pricingCatalog = pricingCatalog;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

}
