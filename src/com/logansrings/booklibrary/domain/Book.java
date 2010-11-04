package com.logansrings.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.logansrings.booklibrary.app.ApplicationContext;
import com.logansrings.booklibrary.app.ApplicationUtilities;
import com.logansrings.booklibrary.persistence.Persistable;
import com.logansrings.booklibrary.persistence.PersistenceDelegate;
import com.logansrings.booklibrary.util.UniqueId;

public class Book implements Persistable{

	private String persistableMode;
	private Long id;
	private String title = "";
	private Author author;
	private Long authorId;
	private boolean valid;
	private String context = "";
	private static PersistenceDelegate persistenceDelegate = null;

	public static void main(String[] args) throws ServletException {
//		Book book = new Book("Mythical Man Month", Author.getTestAuthor());
//		System.out.println(Book.getTestBook().toString());
		Book.getAll();
	}
	

	private Book() {}
	
	public Book(String title, Author author) {
		this.title = title;
		this.author = author;		
		if (ApplicationUtilities.isEmpty(title)) {
			valid = false;
			context = "must have title";
		} else {
			findByTitle();
			if (isNotValid()) {
				persistBook();
			}
		}
	}

	public Book(Long bookId) {
		this.id = bookId;
		findById();
		author = new Author(authorId);
	}

	private void findById() {
		persistableMode = "findId";
		List<Object> list = getPersistenceDelegate().findOne(this);
		if (list.size() == 0) {
			title = "N/A";
			valid = false;
		} else {
			valid = true;
			title = (String) list.get(1);
			authorId = (Long)list.get(2);
		}
	}

	private void findByTitle() {
		persistableMode = "findTitle";
		List<Object> list = getPersistenceDelegate().findOne(this);
		if (list.size() == 0) {
			valid = false;
		} else {
			valid = true;
			id = (Long)list.get(0);
		}
	}

	public void persistBook() {
		persistableMode = "create";
		updateId();			
		if (getPersistenceDelegate().persist(this)) {
			// ok, expected
		} else {
			valid = false;
			context = "unable to persist book";
		}
	}
	
	public String getToString() {
		return toString();
	}
	public String toString() {
		return String.format("[%s] id:%d title:%s valid:%s context:%s author:%s",
				"Book", id, title, valid, context, author.toString());
	}

	public static Book getTestBook() {
		Book book = new Book();
		book.id = 1L;
		book.title = "bookTitle";
		book.author = Author.getTestAuthor();		
		book.valid = true;
		book.context = "a context";
		return book;
	}

	public int getColumnCount() {
		return 3;
	}

	public String[] getColumnNames() {
		if ("findId".equals(persistableMode)) {
			return new String[]{"id"};
		} else if ("findTitle".equals(persistableMode)) {
			return new String[]{"title"};
		} else {
			return new String[]{"id", "title", "authorId"};
		}
	}

	public Object[] getColumnValues() {
		if ("findId".equals(persistableMode)) {
			return new Object[]{id};
		} else if ("findTitle".equals(persistableMode)) {
			return new Object[]{title};
		} else {
			return new Object[]{id, title, author.getId()};
		}
	}

	public String getTableName() {
		return "book";
	}

	static void setPersistenceDelegate(PersistenceDelegate persistenceDelegate) {
		Book.persistenceDelegate = persistenceDelegate;
	}

	static private PersistenceDelegate getPersistenceDelegate() {
		if (persistenceDelegate == null) {
			return ApplicationContext.getPersistenceDelegate();
		} else {
			return persistenceDelegate;
		}		
	}

	String getTitle() {
		return title; 
	}
	
	Long getId() {
		return id; 
	}

	Long getAuthorId() {
		return authorId; 
	}

	String getAuthorFirstName() {
		return author.getFirstName();
	}
	String getAuthorLastName() {
		return author.getLastName();
	}
	
	private void updateId() {
		id = UniqueId.getId();
	}

	public boolean isValid() {
		return valid;
	}

	public boolean isNotValid() {
		return ! isValid();
	}

	public static List<Book> getAll() {
		List<Book> books = new ArrayList<Book>();
		List<List<Object>> findList = getPersistenceDelegate().findAll(new Book());
		if (findList.size() == 0) {
			// nothing to do
		} else {
			for (List<Object> objectList : findList) {
				Book book = new Book();
				book.id = (Long) objectList.get(0);
				book.title = (String) objectList.get(1);
				book.authorId = (Long) objectList.get(2);
				book.author = new Author(book.authorId);
				books.add(book);
			}
		}
		return books;
	}
}
