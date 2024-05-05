package com.example.demo.dto;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dao.Person;
import com.example.demo.dao.PersonDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class PersonDto implements PersonDao<Person>{
	private static final long serialVersionUID = 1L;
	private static final String SINGLE_QOTE = "'";
	
	// 起動時にEntityManagerセット
	@PersistenceContext
	private EntityManager entityManager;
	
	public PersonDto() {
		super();
	}
	
	@Override
	public List<Person> getAll(){
		//クエリ文の生成
		Query query = entityManager.createQuery("from Person");
		//クエリ実行
		@SuppressWarnings("unchecked")
		List<Person> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public List<Person> findById(long id) {
		//クエリ文の生成
		Query query = entityManager.createQuery("from Person where id =" + id );
		//クエリ実行
		@SuppressWarnings("unchecked")
		List<Person> list = query.getResultList();
		return list;
	}

	@Override
	public List<Person> findByName(String name) {
		// クエリ文の生成　
		Query query = entityManager.createQuery("from Person where name like " + SINGLE_QOTE + "%" + name + "%" + SINGLE_QOTE);
		//クエリ実行
		@SuppressWarnings("unchecked")
		List<Person> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> find(String fstr) {
		List<Person> list = null;
		String qstr = "from Person where id = ?1 or name like ?2 or mail like ?3";
		Long fid = 0L;
		try {
			fid = Long.parseLong(fstr);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
		Query query = entityManager.createQuery(qstr)
				.setParameter(1, fid)
				.setParameter(2, "%" + fstr +"%")
				.setParameter(3, fstr + "%@%");
		//クエリ実行		
		list  = query.getResultList();
		return list;
	}
	
	
}
