package com.zumbieland.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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