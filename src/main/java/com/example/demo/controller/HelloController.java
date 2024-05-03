package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {
	String entry = "index";
	Boolean flg = false;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		flg = false;
		String msgStr = "フォームを送信してください。";
		mav.addObject("flg",flg);
		mav.addObject("msg",msgStr);
		mav.setViewName(entry);
		return mav;
	}
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public ModelAndView form(
			@RequestParam(value = "check1",required = false) boolean check1,
			@RequestParam(value = "radio1",required = false) String radio1,
			@RequestParam(value = "select1",required = false) String select1,
			@RequestParam(value = "select2",required = false) String[] select2,
			ModelAndView mav) {
		List<String> resList = new ArrayList<>();
		
		try {
			resList.add("check: " + check1);
			resList.add("radio: " + radio1);
			resList.add("select: " + select1);
		} catch (NullPointerException e) {}
		try {
			String select2Str = select2[0];
			for(int i=1; i < select2.length;i++) {
				select2Str += ", " + select2[i];
			}
			resList.add("select2:" + select2Str);
		} catch (NullPointerException e) {
			resList.add("null");
		}
		
		flg = true;
		mav.addObject("flg",flg);
		mav.addObject("msg",resList);
		mav.setViewName(entry);
		return mav;
	}
	
}
