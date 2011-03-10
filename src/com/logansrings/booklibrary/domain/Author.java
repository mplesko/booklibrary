package com.logansrings.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.logansrings.booklibrary.app.ApplicationContext;
import com.logansrings.booklibrary.app.ApplicationUtilities;
import com.logansrings.booklibrary.persistence.Persistable;
import com.logansrings.booklibrary.persistence.PersistenceDelegate;
import com.logansrings.booklibrary.util.UniqueId;

public class Author implements Persistable {

	private String persistableMode;
	private Long id;
	private String firstName;
	private String lastName;
	private boolean valid;
	private String context = "";
	private static PersistenceDelegate persistenceDelegate = null;
	
	public static void main(String[] args) throws ServletException {
//		new ApplicationContext().init();
//		Author author = new Author("Fred", "Brooks");
		System.out.println(Author.getTestAuthor().toString());
	}
	
	private Author() {}
	
	public Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		if (ApplicationUtilities.isEmpty(firstName, lastName)) {
			valid = false;
			context = "must have firstName and lastName";
		} else {
			findByName();
			if (isNotValid()) {
				persistAuthor();
			}
		}
	}
	
	public Author(Long authorId) {
		this.id = authorId;
		findById();
	}

	private void findById() {
		persistableMode = "findId";
		Persistable persistable = getPersistenceDelegate().findOne(this);
		if (persistable == null) {
			valid = false;
		} else {
			valid = true;
			firstName = ((Author)persistable).getFirstName();
			lastName = ((Author)persistable).getLastName();
		}
	}

	private void findByName() {
		persistableMode = "findName";
		Persistable persistable = getPersistenceDelegate().findOne(this);
		if (persistable == null) {
			valid = false;
		} else {
			valid = true;
			id = persistable.getId();
		}
	}
	
	public void persistAuthor() {
		persistableMode = "create";
		updateId();			
		if (getPersistenceDelegate().persist(this)) {
			// ok, expected
		} else {
			valid = false;
			context = "unable to persist author";
		}
	}
	
	public String toString() {
		return String.format("[%s] id:%d firstName:%s lastName:%s valid:%s context:%s ",
				"Author", id, firstName, lastName, valid, context);
	}

	public static Author getTestAuthor() {
		Author author = new Author();
		author.id = 1L;
		author.firstName = "authorFirstName";
		author.lastName = "authorLastName";
		author.valid = true;
		author.context = "a context";
		return author;
	}

	public int getColumnCount() {
		return 3;
	}

	public String[] getColumnNames() {
		if ("findId".equals(persistableMode)) {
			return new String[]{"id"};
		} else if ("findName".equals(persistableMode)) {
			return new String[]{"firstName", "lastName"};
		} else {
			return new String[]{"id", "firstName", "lastName"};
		}
	}

	public Object[] getColumnValues() {
		if ("findId".equals(persistableMode)) {
			return new Object[]{id};
		} else if ("findName".equals(persistableMode)) {
			return new Object[]{firstName, lastName};
		} else {
			return new Object[]{id, firstName, lastName};
		}
	}

	public String getTableName() {
		return "author";
	}

	public boolean isValid() {
		return valid;
	}

	public boolean isNotValid() {
		return ! isValid();
	}

	static void setPersistenceDelegate(PersistenceDelegate persistenceDelegate) {
		Author.persistenceDelegate = persistenceDelegate;
	}
	
	static private PersistenceDelegate getPersistenceDelegate() {
		if (persistenceDelegate == null) {
			return ApplicationContext.getPersistenceDelegate();
		} else {
			return persistenceDelegate;
		}		
	}

	private void updateId() {
		id = UniqueId.getId();
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
	
	public Long getId() {
		return id;
	}
	
	String getFirstName() {
		return firstName;
	}
	String getLastName() {
		return lastName;
	}

	public static List<Author> getAll() {
		List<Author> authors = new ArrayList<Author>();
		List<Persistable> findList = getPersistenceDelegate().findAll(new Author());
		if (findList.size() == 0) {
			// nothing to do
		} else {
			for (Persistable persistable : findList) {
				authors.add((Author)persistable);
			}
		}
		return authors;
	}
	
	@Override
	public Author newFromDBColumns(List<Object> objectList) {
		Author author = new Author();
		if (objectList.size() == getColumnCount()) {
			author.id = (Long) objectList.get(0);
			author.firstName = (String) objectList.get(1);
			author.lastName = (String) objectList.get(2);
		}
		return author;
	}

}
