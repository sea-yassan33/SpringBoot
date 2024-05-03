package com.example.demo.reqositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
