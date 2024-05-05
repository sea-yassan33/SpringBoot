package com.example.demo.reqositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	//IDでエンティティを検索し取り出す処理
	public Optional<Person> findById(Long id);
	
	/**
	 * Like検索 <br>
	 * fintBy[ColumName]Like <br>
	 * SQL: from [DataName] where [CollumName] like ?[検索内容]
	 */
	public List<Person> findByIdLike(String name);
	
	/**
	 * Between <br>
	 * findBy[ColumName]Between <br>
	 * SQL: from [DataName] where [CollumName] ?[検索値1] and ?[検索値2]
	 */
	public List<Person> findByAgeBetween(int age1, int age2);
}
