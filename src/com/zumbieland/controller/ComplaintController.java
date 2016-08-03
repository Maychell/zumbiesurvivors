package com.zumbieland.controller;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zumbieland.dao.JDBCComplaintDAO;
import com.zumbieland.dao.JDBCSurvivorDAO;
import com.zumbieland.model.Complaint;
import com.zumbieland.model.Survivor;

@RestController
public class ComplaintController {
	
	private JDBCComplaintDAO dao;
	private JDBCSurvivorDAO daoSurvivor;
	
	@Autowired
	public ComplaintController(JDBCComplaintDAO dao, JDBCSurvivorDAO daoSurvivor){
	     this.dao = dao;
	     this.daoSurvivor = daoSurvivor;
	}
	
	/**
	 * register a new complaint
	 * to the passed survivor through RESTful API
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/complaint", method = RequestMethod.POST, headers = {"content-type=application/json,application/xml"})
	public String addComplaint(@ModelAttribute Survivor survivor, ModelMap model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> messageHandler = new HashMap<>();
		survivor = daoSurvivor.getSurvivorById(survivor.getId());
		
		try {
			//survivor not found
			if(survivor == null) {
				messageHandler.put("message", "The survivor is not found.");
				return mapper.writeValueAsString(messageHandler);
			}
			
			//if is already infected, do nothing.
			if(survivor.isInfected()) {
				messageHandler.put("message", "The survivor is already infected.");
				return mapper.writeValueAsString(messageHandler);
			}
			
			int complaintsCount = dao.getCountComplaintsBySurvivor(survivor);
			
			//if less than three infection complaints, count one more complaint
			if(complaintsCount < 2) {
				Complaint complaint = new Complaint(survivor);
				dao.create(complaint);
				messageHandler.put("message", "The survivor has "+(complaintsCount+1)+" complaint(s).");
				return mapper.writeValueAsString(messageHandler);
			}
			
			//the survivor has now three complaints, therefore its infected now
			Complaint complaint = new Complaint(survivor);
			dao.create(complaint);
			survivor.setInfected(true);
			daoSurvivor.update(survivor);
			
			messageHandler.put("message", "The survivor is now infected.");
			return mapper.writeValueAsString(messageHandler);
		} catch (Exception e) {
			return null;
		}

	}

}
