package com.zumbieland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zumbieland.dao.JDBCSurvivorDAO;
import com.zumbieland.model.Survivor;

@Controller
public class SurvivorController {
	
	private JDBCSurvivorDAO dao;
	
	@Autowired
	public SurvivorController(JDBCSurvivorDAO dao){
	     this.dao = dao;
	}
	
	@RequestMapping(value = "/addZumbie", method = RequestMethod.GET)
	public String adiciona(ModelMap model) {
//		JdbcTarefaDao dao = new JdbcTarefaDao();
//	    dao.adiciona(tarefa);
//	    return survivor.toString();
//		ApplicationContext context = 
//	             new ClassPathXmlApplicationContext("classpath*:**/servlet.xml");
//
//		JDBCSurvivorDAO dao = 
//				(JDBCSurvivorDAO) context.getBean("JDBCSurvivorDAO");
		
		Survivor test = new Survivor();
		test.setId((long) 10);
		test.setAge(12);
		test.setLatitude("fdsfs");
		test.setLongitude("fdsfs");
		test.setGender('m');
		test.setName("dsadad");
		dao.create(test);
		
		model.addAttribute("tarefa", test.toString());
		return "survivor/add_zumbie";
	}
}