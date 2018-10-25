package com.spring.mvc.jpa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.jpa.dao.PersonDao;
import com.spring.mvc.jpa.model.Person;

@Controller
//@RequestMapping("/person/")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonDao personDao;
	

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> savePerson(@RequestBody Person jsonString) {
		logger.debug("Received postback on person "+jsonString);
		HashMap<String, String> map=new HashMap<String, String>();
		personDao.save(jsonString);
		map.put("result", "success");
		return map;
		
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST,value="list")
	public @ResponseBody List<HashMap<String, String>> listPeople() {
		logger.debug("Received request to list persons");
		ModelAndView mav = new ModelAndView();
		List<Person> people = personDao.getPeople();
		
		List<HashMap<String, String>> list =new ArrayList<HashMap<String,String>>();
		for(Person p:people) {
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("id", p.getId()+"");
			map.put("firstname", p.getFirstName());
			map.put("lastname", p.getLastName());
			list.add(map);
		}
		
		logger.debug("Person Listing count = "+people.size());
	
		return list;
		
	}
	
	
	
	
/*	@RequestMapping(method=RequestMethod.GET,value="edit")
	public ModelAndView editPerson(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit person id : "+id);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("edit");
 		Person person = null;
 		if (id == null) {
 			person = new Person();
 		} else {
 			person = personDao.find(id);
 		}
 		
 		mav.addObject("person", person);
		return mav;
		
	}*/

}
