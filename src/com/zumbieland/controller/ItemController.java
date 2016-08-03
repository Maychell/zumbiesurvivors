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

import com.zumbieland.dao.JDBCItemDAO;
import com.zumbieland.model.Item;

@RestController
public class ItemController {

private JDBCItemDAO dao;
	
	@Autowired
	public ItemController(JDBCItemDAO dao){
	     this.dao = dao;
	}
	
	/**
	 * create an item through RESTful API
	 * @param item
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item", method = RequestMethod.POST, headers = {"content-type=application/json,application/xml"})
	public String addItem(@ModelAttribute Item item, ModelMap model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> errorHandler = new HashMap<>();
		try {
			if(item == null || item.getName() == null) {
				errorHandler.put("error", "Item is missing.");
				return mapper.writeValueAsString(errorHandler);
			}
			dao.create(item);
			return mapper.writeValueAsString(item);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * updates an item through RESTful API
	 * @param id
	 * @param item
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/{id}", method = RequestMethod.PUT, headers = {"content-type=application/json,application/xml"})
	public String updateItem(@PathVariable("id") long id, @ModelAttribute Item item, ModelMap model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> errorHandler = new HashMap<>();
		try {
			if(id <= 0) {
				errorHandler.put("error", "Item is missing.");
				return mapper.writeValueAsString(errorHandler);
			}
			Item oldItem = dao.getItemById(id);
			oldItem.setName(item.getName());
			oldItem.setPoints(item.getPoints());
			dao.update(oldItem);
			return mapper.writeValueAsString(oldItem);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * returns all items data through RESTful API
	 * @return
	 */
	@RequestMapping(value = "/item", method = RequestMethod.GET, headers="Accept=application/json")
	public String getItems() {
		ObjectMapper mapper = new ObjectMapper();
		List<Item> items = dao.listItems();
		if(items.isEmpty())
			return null;
		try {
			return mapper.writeValueAsString(items);
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
	 * returns an single item by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET, headers="Accept=application/json")
	public String getItem(@PathVariable int id) {
		ObjectMapper mapper = new ObjectMapper();
		Item item = dao.getItemById((long) id);
		if(item == null)
			return null;
		try {
			return mapper.writeValueAsString(item);
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
