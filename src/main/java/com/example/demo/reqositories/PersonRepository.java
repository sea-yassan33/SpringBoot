package com.example.demo.reqositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	//IDでエンティティを検索し取り出す処理
	public Optional<Person> findById(Long id);
}
