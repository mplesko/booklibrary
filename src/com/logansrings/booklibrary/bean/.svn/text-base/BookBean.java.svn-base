package com.logansrings.booklibrary.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.logansrings.booklibrary.domain.Author;
import com.logansrings.booklibrary.domain.Book;
import com.logansrings.booklibrary.domain.ObjectFactory;

public class BookBean {
	private Long id;
	private String title;
	private String authorFirstName;
	private String authorLastName;
	private Long authorId;
	private Long selectedAuthorId;
	private boolean markedForDeletion;
	private boolean markedForAddition;

	public BookBean() {}

	public BookBean(String title, String authorFirstName, String authorLastName) {
		this(null, title, null, authorFirstName, authorLastName);
		markedForAddition = true;
	}

	public BookBean(
			Long id, String title, Long authorId, String authorFirstName, String authorLastName) {
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthorName() {
		return authorFirstName + " " + authorLastName;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public boolean isMarkedForDeletion() {
		return markedForDeletion;
	}

	public void setMarkedForDeletion(boolean markedForDeletion) {
		this.markedForDeletion = markedForDeletion;
	}

	public boolean isMarkedForAddition() {
		return markedForAddition;
	}

	public void setMarkedForAddition(boolean markedForAddition) {
		this.markedForAddition = markedForAddition;
	}

	public List<BookBean> getBooks() {
		return ObjectFactory.createBookBeans(Book.getAll());
	}

	public String addBook() {
		System.out.println("In BookBean.addBook()");
		return null;
	}
	
	public void setSelectedAuthorId(Object value) {
		selectedAuthorId = (Long) value;
		System.out.println("In BookBean.setSelectedAuthorId()");
	}

	public List<SelectItem> getSelectBooks() {
		List<SelectItem> selectBooks = new ArrayList<SelectItem>();
		for (BookBean bookBean : getBooks()) {
			selectBooks.add(new SelectItem(bookBean.getAuthorId(), bookBean.getAuthorName()));
		}
		return selectBooks;
	}

	public Object getAuthorId() {
		return authorId;
	}

	public Long getSelectedAuthorId() {
		return selectedAuthorId;
	}

	public void setSelectedAuthorId(Long selectedAuthorId) {
		this.selectedAuthorId = selectedAuthorId;
	}

}
