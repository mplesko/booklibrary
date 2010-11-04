package com.logansrings.booklibrary.bean;

import java.util.List;

import com.logansrings.booklibrary.domain.Author;
import com.logansrings.booklibrary.domain.ObjectFactory;

public class AuthorBean {

	private String firstName;
	private String lastName;
	
	public AuthorBean() {}
	
	public AuthorBean(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String addAuthor() {
		Author author = new Author(firstName, lastName);
		clear();
		return null;
	}
	
	private void clear() {
		firstName = "";
		lastName = "";
	}

	public String toString() {
		return String.format("[%s] id:%d firstName:%s lastName:%s ",
				"Author", firstName, lastName);
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
	

	public List<AuthorBean> getAuthors() {
		return ObjectFactory.createAuthorBeans(Author.getAll());
	}

}
