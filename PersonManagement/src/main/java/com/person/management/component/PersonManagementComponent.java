package com.person.management.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.person.management.entity.Person;
import com.person.management.repository.PersonRepository;

@Component
public class PersonManagementComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonManagementComponent.class);
	   
	PersonRepository personRepository;
	
	@Autowired
	PersonManagementComponent(PersonRepository personRepository)
	{
		this.personRepository = personRepository;
	}
	
	//Search Person Based on the Id, First Name, LastName and Age
	public Person searchPerson(Integer id, String firstName, String lastName, Integer age)
	{
		Person person = new Person();
		if (id !=null)
		{
			logger.info("Search Based On Id");
			person = personRepository.findById(id.longValue());
		}
		else
		{
			 logger.info("Search based on firstName,LastName and Age");
			 person = personRepository.findByFirstNameAndLastNameAndAge(firstName, lastName, age);
		}	   
		return  person;	
	}

	//Save Person Record
	public Long savePerson(Person person)
	{
		logger.info("Save Person Record");
		Long id = null;
	    id = personRepository.save(person).getId();
		return id;
	}
	
	//Check Duplicate Person Record in the Database
	public boolean checkDuplicatePerson(Person person)
	{
		Person personObj = personRepository.findByFirstNameAndLastNameAndAge(person.getFirstName(), person.getLastName(), person.getAge());
		if (personObj==null)
		{
			return false;
		}
		return true;
	}
	
	//Update Person Record
	public void updatePerson(Long id, String firstName, String lastName, Integer age, String favoritecolor, String hobby)
	{
		Person person = personRepository.getOne(id);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setAge(age);
		person.setFavoriteColor(favoritecolor);
		person.setHobby(hobby);
		personRepository.save(person);		
		logger.info("Update Person Record in Database");
	}
	
	//Delete Person Record
	public void deletePerson(Long id)
	{
		Person person = personRepository.getOne(id);
		personRepository.delete(person);	
	}
}
