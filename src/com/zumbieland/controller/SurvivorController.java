package com.zumbieland.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.zumbieland.dao.JDBCSurvivorDAO;
import com.zumbieland.model.Survivor;

@RestController
public class SurvivorController {
	
	private JDBCSurvivorDAO dao;
	
	@Autowired
	public SurvivorController(JDBCSurvivorDAO dao){
	     this.dao = dao;
	}
	
	/**
	 * create a survivor through RESTful API
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/survivor", method = RequestMethod.POST, headers = {"content-type=application/json,application/xml"})
	public String addSurvivor(@ModelAttribute Survivor survivor, ModelMap model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> errorHandler = new HashMap<>();
		try {
			if(survivor == null || survivor.getName() == null) {
				errorHandler.put("error", "Survivor is missing.");
				return mapper.writeValueAsString(errorHandler);
			}
			dao.create(survivor);
			return mapper.writeValueAsString(survivor);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * updates a survivor through RESTful API
	 * @param id
	 * @param survivor
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/survivor/{id}", method = RequestMethod.PUT, headers = {"content-type=application/json,application/xml"})
	public String updateSurvivorLocation(@PathVariable("id") long id, @ModelAttribute Survivor survivor, ModelMap model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> errorHandler = new HashMap<>();
		try {
			if(id <= 0) {
				errorHandler.put("error", "Survivor is missing.");
				return mapper.writeValueAsString(errorHandler);
			}
			Survivor oldSurvivor = dao.getSurvivorById(id);
			oldSurvivor.setLatitude(survivor.getLatitude());
			oldSurvivor.setLongitude(survivor.getLongitude());
			dao.update(oldSurvivor);
			return mapper.writeValueAsString(oldSurvivor);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * returns all survivors data through RESTful API
	 * @return
	 */
	@RequestMapping(value = "/survivor", method = RequestMethod.GET, headers="Accept=application/json")
	public String getSurvivors() {
		ObjectMapper mapper = new ObjectMapper();
		List<Survivor> survivors = dao.listSurvivors();
		if(survivors.isEmpty())
			return null;
		try {
			return mapper.writeValueAsString(survivors);
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
	 * returns an single survivor by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/survivor/{id}", method = RequestMethod.GET, headers="Accept=application/json")
	public String getSurvivor(@PathVariable int id) {
		ObjectMapper mapper = new ObjectMapper();
		Survivor survivor = dao.getSurvivorById((long) id);
		if(survivor == null)
			return null;
		try {
			return mapper.writeValueAsString(survivor);
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