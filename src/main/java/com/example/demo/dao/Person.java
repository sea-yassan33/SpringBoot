package com.example.demo.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Person {

	/*
	 * プライマリキーとして表示
	 * 値は自動生成
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message = "名前の記入をお願いします")
	private String name;
	
	@Column(length = 200, nullable = true)
	@Email(message = "正しいメールアドレスを入力してください")
	private String mail;
	
	@Column(nullable = true)
	@Min(value = 0, message = "0より小さい数字は入力出来ません。")
	@Max(value = 200, message = "200より大きい数字は入力できません。")
	private Integer age;
	
	@Column(nullable = true)
	private String memo;
	
	public long getId() {
		return id;
	}public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
