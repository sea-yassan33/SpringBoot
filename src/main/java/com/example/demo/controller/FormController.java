package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.Person;
import com.example.demo.dto.PersonDto;
import com.example.demo.reqositories.PersonRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Controller
public class FormController {
	
	// PersonRepositoryインスタンスの設定
	@Autowired
	PersonRepository repository;
	
	// PersonDtoインスタンスの設定
	@Autowired
	PersonDto dto;
	
	String entry = "";
	
	@RequestMapping("/test/")
	public ModelAndView index(@ModelAttribute("formModel") Person person,ModelAndView mav) {
		entry = "form";
		mav.setViewName(entry);
		mav.addObject("title", "Hello page");
		mav.addObject("msg","this is JPA sample data.");
		Iterable<Person> list = dto.getAll();
		mav.addObject("data",list);
		
		return mav;
	}
	
	@RequestMapping(value= "/test/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(@ModelAttribute("formModel") @Validated Person person,BindingResult result,ModelAndView mav) {
		entry = "form";
		ModelAndView res = null;
		//System.out.println(result.getFieldErrors());
		//バリデーションエラーの有無
		if(!result.hasErrors()) {
			// formで取得した内容を保存
			repository.saveAndFlush(person);
			return new ModelAndView("redirect:/test/");
		}else {
			mav.setViewName(entry);
			mav.addObject("title", "Hello page");
			mav.addObject("msg","入力エラーです");
			Iterable<Person> list = dto.getAll();
			mav.addObject("data",list);
			res = mav;
		}
		
		return res;
	}
	
	/**
	 * editに対するコントロール
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute Person person, @PathVariable Long id, ModelAndView mav) {
		entry = "edit";
		mav.setViewName(entry);
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
	
	/**
	 * delitに対するコントロール
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleit(@PathVariable Long id, ModelAndView mav) {
		entry = "delete";
		mav.setViewName(entry);
		mav.addObject("title", "Delet Person.");
		mav.addObject("msg", "こちらのレコードを削除しますか？");
		Optional<Person> data = repository.findById(id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value = "/delete/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView remobe(@RequestParam Long idDelet,ModelAndView mav) {
		// idDelitで渡されたパラメータを素に対象のidを削除
		repository.deleteById(idDelet);
		return new ModelAndView("redirect:/test/");
	}
	
	/**
	 * findに対するコントロール
	 */
	@RequestMapping(value = "/find/", method = RequestMethod.GET)
	public ModelAndView findPage(ModelAndView mav) {
		entry = "find";
		mav.setViewName(entry);
		mav.addObject("title","検索画面");
		mav.addObject("msg","検索をお願いします。");
		Iterable<Person> list = dto.getAll();
		mav.addObject("data", list);
		return mav;
	}
	
	@RequestMapping(value = "/find/", method = RequestMethod.POST)
	public ModelAndView search (HttpServletRequest request, ModelAndView mav) {
		entry = "find";
		String parameterStr = "findStr"; 
		String parameterStr2 = "findNameStr"; 
		mav.setViewName(entry);
		String param = request.getParameter(parameterStr);
		String param2 = request.getParameter(parameterStr2);
		if(param == "" && param2 == "") {
			mav = new ModelAndView("redirect:/find/");
		} else {
			if(!(StringUtils.isEmpty(param))) {
				mav.addObject("title","検索画面");
				mav.addObject("msg","「" + param + "」のID検索結果");
				Iterable<Person> list = dto.findById(Integer.parseInt(param)) ;
				mav.addObject("data", list);
			}else {
				mav.addObject("title","検索画面");
				mav.addObject("msg","「" + param2 + "」が含まれている名前検索結果");
				Iterable<Person> list = dto.findByName(param2) ;
				mav.addObject("data", list);
			}
		}
		
		return mav;
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
		//3つ目ダミーデータ
		Person p3 = new Person();
		p3.setName("emily");
		p3.setAge(40);
		p3.setMail("emily@example.com");
		repository.saveAndFlush(p3);
		//4つ目ダミーデータ
		Person p4 = new Person();
		p4.setName("mark");
		p4.setAge(28);
		p4.setMail("mark@example.com");
		repository.saveAndFlush(p4);
		//4つ目ダミーデータ
		Person p5 = new Person();
		p5.setName("kao");
		p5.setAge(58);
		p5.setMail("kao@example.com");
		repository.saveAndFlush(p5);
	}
}
