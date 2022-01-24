package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tool {
	private static String toolCode; 
	private static String toolType;
	private static String brand;
	private static JsonNode toolCatalog = initCatalog();
	
	private static JsonNode initCatalog() {
		//read in json parse in 
		ObjectMapper objMapper = new ObjectMapper();
		try {
			JsonNode catalog = objMapper.readTree(Files.readAllBytes(Paths.get("src/model/toolCatalog.json")));
			return catalog;
		}catch(IOException ioExeption){
			System.out.print("An error occured parsing the tool catalog");
			return null;
		}
	}
	private Tool() {};
	public Tool(String requestedToolCode) {
		Tool.setToolCode(requestedToolCode);
		for(JsonNode toolInfo : toolCatalog.get("tools")) {
			if(toolInfo.get("typeCode").asText().equals(requestedToolCode)) {
				Tool.setToolType(toolInfo.get("typeName").asText());
				Tool.setBrand(toolInfo.get("brand").asText());
			}
		}
	};
	
	public static String getToolCode() {
		return toolCode;
	}
	
	private static void setToolCode(String toolCode) {
		Tool.toolCode = toolCode;
	}
	
	public static String getToolType() {
		return toolType;
	}
	
	private static void setToolType(String toolType) {
		Tool.toolType = toolType;
	}
	
	public static String getBrand() {
		return brand;
	}
	
	private static void setBrand(String brand) {
		Tool.brand = brand;
	}
	
}
