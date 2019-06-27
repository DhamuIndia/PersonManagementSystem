package com.person.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.person.management.entity.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByFirstNameAndLastNameAndAge(String firstName, String lastName,Integer age);
	Person findById(long Id);
}
