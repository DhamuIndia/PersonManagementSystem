package com.person.management.component;

import com.person.management.entity.Person;

public class UIData {
	
	private SearchQuery searchQuery;
	
	private Person person;

	public SearchQuery getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(SearchQuery searchQuery) {
		this.searchQuery = searchQuery;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	

}
