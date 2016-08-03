package com.zumbieland.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zumbieland.dao.JDBCInventoryDAO;
import com.zumbieland.dao.JDBCSurvivorDAO;

@RestController
public class ReportController {

	private JDBCInventoryDAO daoInventory;
	private JDBCSurvivorDAO daoSurvivor;
	
	@Autowired
	public ReportController(JDBCInventoryDAO daoInventory, JDBCSurvivorDAO daoSurvivor){
	     this.daoInventory = daoInventory;
	     this.daoSurvivor = daoSurvivor;
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
}
