package com.zumbieland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zumbieland.model.Survivor;

@Controller
public class SurvivorController {
	
	@RequestMapping(value = "/addZumbie", method = RequestMethod.GET)
	public String adiciona(ModelMap model) {
//		JdbcTarefaDao dao = new JdbcTarefaDao();
//	    dao.adiciona(tarefa);
//	    return survivor.toString();
		Survivor test = new Survivor();
		test.setId((long) 10);
		test.setAge(12);
		test.setLatitude("fdsfs");
		test.setLongitude("fdsfs");
		test.setGender('m');
		test.setName("dsadad");
		model.addAttribute("tarefa", test.toString());
		return "survivor/add_zumbie";
	}
}