package com.zumbieland.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zumbieland.dao.JDBCInventoryDAO;
import com.zumbieland.model.Inventory;
import com.zumbieland.model.Survivor;

@RestController
public class InventoryController {

private JDBCInventoryDAO dao;
	
	@Autowired
	public InventoryController(JDBCInventoryDAO dao){
	     this.dao = dao;
	}
	
	/**
	 * create a inventory through RESTful API
	 * @param inventory
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inventory", method = RequestMethod.POST, headers = {"content-type=application/json,application/xml"})
	public String addSurvivor(@ModelAttribute Inventory inventory, ModelMap model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> errorHandler = new HashMap<>();
		try {
			if(inventory == null || inventory.getSurvivor().getId() == null) {
				errorHandler.put("error", "Inventory is missing.");
				return mapper.writeValueAsString(errorHandler);
			}
			dao.create(inventory);
			return mapper.writeValueAsString(inventory);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * updates a inventory through RESTful API
	 * @param id
	 * @param inventory
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.PUT, headers = {"content-type=application/json,application/xml"})
	public String updateSurvivorLocation(@PathVariable("id") long id, @ModelAttribute Inventory inventory, ModelMap model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> errorHandler = new HashMap<>();
		try {
			if(id <= 0) {
				errorHandler.put("error", "Inventory is missing.");
				return mapper.writeValueAsString(errorHandler);
			}
			Inventory oldInventory = dao.getInventoryById(id);
			oldInventory.setSurvivor(inventory.getSurvivor());
			oldInventory.setItem(inventory.getItem());
			dao.update(oldInventory);
			return mapper.writeValueAsString(oldInventory);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * returns all inventories data that the survivor owns through RESTful API
	 * @param survivor
	 * @return
	 */
	@RequestMapping(value = "/inventory", method = RequestMethod.GET, headers="Accept=application/json")
	public String getInventories(@ModelAttribute Survivor survivor) {
		ObjectMapper mapper = new ObjectMapper();
		List<Inventory> inventories = dao.listSurvivorInventory(survivor);
		if(inventories.isEmpty())
			return null;
		try {
			return mapper.writeValueAsString(inventories);
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
	 * returns an single inventory by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET, headers="Accept=application/json")
	public String getInventory(@PathVariable int id) {
		ObjectMapper mapper = new ObjectMapper();
		Inventory inventory = dao.getInventoryById((long) id);
		if(inventory == null)
			return null;
		try {
			return mapper.writeValueAsString(inventory);
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