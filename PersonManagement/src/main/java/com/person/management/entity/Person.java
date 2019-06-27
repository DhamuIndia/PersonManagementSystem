package com.person.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
    
    private String firstName;
    private String lastName;
    private Integer age;
    private String favoriteColor;
    private String hobby;
    
    public Person()
    {
    	
    }
    public Person(String firstName, String lastName, Integer age, String favoriteColor, String hobby)
    {
    	this.firstName = firstName;
    	this.lastName  = lastName;
    	this.age = age;
    	this.favoriteColor = favoriteColor;
    	this.hobby = hobby;
    }
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getFavoriteColor() {
		return favoriteColor;
	}
	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
  
}
