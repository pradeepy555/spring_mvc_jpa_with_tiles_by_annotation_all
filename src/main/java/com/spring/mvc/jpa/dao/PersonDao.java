package com.spring.mvc.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.jpa.model.Person;
@Repository
public class PersonDao {

	@PersistenceContext
	EntityManager em;
	
	public Person find(Long id) {
		return em.find(Person.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getPeople() {
		return em.createQuery("select p from Person p").getResultList();
	}
	
	@Transactional
	public Person save(Person person) {
		if (person.getId() == null) {
			em.persist(person);
			return person;
		} else {
			return em.merge(person);
		}		
	}	
	
}
