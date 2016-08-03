package com.zumbieland.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zumbieland.dao.JDBCInventoryDAO;
import com.zumbieland.dao.JDBCItemDAO;
import com.zumbieland.dao.JDBCSurvivorDAO;
import com.zumbieland.model.Item;

@RestController
public class ReportController {

	private JDBCInventoryDAO daoInventory;
	private JDBCSurvivorDAO daoSurvivor;
	private JDBCItemDAO daoItem;
	
	@Autowired
	public ReportController(JDBCInventoryDAO daoInventory, JDBCSurvivorDAO daoSurvivor, JDBCItemDAO daoItem){
	     this.daoInventory = daoInventory;
	     this.daoSurvivor = daoSurvivor;
	     this.daoItem = daoItem;
	}
	
	/**
	 * returns the percentage of infected survivors
	 * @return
	 */
	@RequestMapping(value = "/reportInfected", method = RequestMethod.GET, headers="Accept=application/json")
	public String getInfectedPercentage() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> messageHandler = new HashMap<>();
		
		int totalSurvivors = daoSurvivor.count();
		int infectedSurvivors = daoSurvivor.countInfected();
		
		try {
			messageHandler.put("report", ""+((float)infectedSurvivors*100/totalSurvivors));
			return mapper.writeValueAsString(messageHandler);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * returns the percentage of non-infected survivors
	 * @return
	 */
	@RequestMapping(value = "/reportNotInfected", method = RequestMethod.GET, headers="Accept=application/json")
	public String getNonInfectedPercentage() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> messageHandler = new HashMap<>();
		
		int totalSurvivors = daoSurvivor.count();
		int notInfectedSurvivors = totalSurvivors - daoSurvivor.countInfected();
		
		try {
			messageHandler.put("report", ""+((float)notInfectedSurvivors*100/totalSurvivors));
			return mapper.writeValueAsString(messageHandler);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * returns the points lost because of infected survivor
	 * @return
	 */
	@RequestMapping(value = "/reportPointsLost", method = RequestMethod.GET, headers="Accept=application/json")
	public String getPointsLost() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> messageHandler = new HashMap<>();
		
		int infectedSurvivors = daoSurvivor.countInfected();
		
		try {
			messageHandler.put("report", ""+(infectedSurvivors*10));
			return mapper.writeValueAsString(messageHandler);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * returns the average of items per survivor
	 * @return
	 */
	@RequestMapping(value = "/reportItemsAverage", method = RequestMethod.GET, headers="Accept=application/json")
	public String getItemsAverage() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Map<String, Float>> messageHandler = new HashMap<>();
		
		List<Item> items = daoItem.listItems();
		Map<String, Float> quantity = new HashMap<>();
		int totalSurvivors = daoSurvivor.count();
		
		for(Item item : items) {
			int totalItems = daoInventory.countByItem(item);
			quantity.put(item.getName(), (float)totalItems/totalSurvivors);
		}
		
		try {
			messageHandler.put("report", quantity);
			return mapper.writeValueAsString(messageHandler);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
