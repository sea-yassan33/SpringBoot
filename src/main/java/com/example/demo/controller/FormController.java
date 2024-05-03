package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.Person;
import com.example.demo.reqositories.PersonRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Controller
public class FormController {
	
	@Autowired
	PersonRepository repository;
	
	String entry = "form";
	
	@RequestMapping("/test/")
	public ModelAndView index(
			@ModelAttribute("formModel")Person person,
			ModelAndView mav) {
		mav.setViewName(entry);
		mav.addObject("title", "Hello page");
		mav.addObject("msg","this is JPA sample data.");
		Iterable<Person> list = repository.findAll();
		mav.addObject("data",list);
		
		return mav;
	}
	
	@RequestMapping(value= "/test/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(
			@ModelAttribute("formModel") Person person,
			ModelAndView mav) {
		// formで取得した内容を保存
		repository.saveAndFlush(person);
		return new ModelAndView("redirect:/test/");
		
	}
	
	/*
	 * editに対するコントロール
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute Person person, @PathVariable Long id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit Person");
		// パラメータから渡されたidを元にデータを取得
		Optional<Person> data = repository.findById(id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	
	@RequestMapping(value = "/edit/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute Person person, ModelAndView mav) {
		repository.saveAndFlush(person);
		return new ModelAndView("redirect:/test/");
	}
	
	/*
	 * データの初期化
	 * H2使用の為、データキャッシュしている。
	 * ダミーのデータを用意
	 */
	@PostConstruct
	public void init() {
		//1つ目ダミーデータ
		Person p1 = new Person();
		p1.setName("mac");
		p1.setAge(31);
		p1.setMail("mac@example.com");
		repository.saveAndFlush(p1);
		//2つ目ダミーデータ
		Person p2 = new Person();
		p2.setName("jyan");
		p2.setAge(22);
		p2.setMail("jyan@example.com");
		repository.saveAndFlush(p2);
	}
}
