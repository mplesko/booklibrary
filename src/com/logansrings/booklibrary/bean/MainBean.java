package com.logansrings.booklibrary.bean;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import com.logansrings.booklibrary.domain.Book;
import com.logansrings.booklibrary.domain.Library;
import com.logansrings.booklibrary.domain.ObjectFactory;

/**
 * Backs the main.jsp page
 * @author mark
 */
public class MainBean {
	private UserBean userBean;
	private Library library;
	private List<BookBean> bookBeans;
	private String addBookTitle;
	private String addAuthorFirstName;
	private String addAuthorLastName;

	public MainBean() {
		System.out.println("MainBean.constructor");
		initialize();
	}

	private void initialize() {
		initializeUserBean();		
		initializeLibrary();
		initializeBookBeans();
	}

	private void initializeUserBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext    elContext    = facesContext.getELContext();
		ELResolver   elResolver   = elContext.getELResolver();
		userBean = (UserBean)elResolver.getValue(elContext, null, "userbean");
	}

	private void initializeLibrary() {
		library = new Library(userBean.getUser());
	}

	private void initializeBookBeans() {
		bookBeans = createBookBeans(library);
	}
	
	private List<BookBean> createBookBeans(Library library) {
		return ObjectFactory.createBookBeans(library);
	}

	public List<BookBean> getBookBeans() {
		if (library == null) {
			initialize();
		}
		return bookBeans;
	}

	public String updateBooks() {
		System.out.println("MainBean.updateBooks()");
		if (haveBooksToAdd() || haveBooksToDelete()) {
			// ok, work to do
		} else {
			return null;
		}
		List<BookBean> booksToAdd = new ArrayList<BookBean>();
		List<BookBean> booksToDelete = new ArrayList<BookBean>();
		for (BookBean book : bookBeans) {
			if (book.isMarkedForDeletion()) {
				booksToDelete.add(book);
			} else if (book.isMarkedForAddition()) {
				booksToAdd.add(book);
			}
		}
		library.updateBooks(createBooks(booksToAdd), createBooks(booksToDelete));
		initializeLibrary();
		initializeBookBeans();
		return null;
	}

	private List<Book> createBooks(List<BookBean> bookBeans) {
		return ObjectFactory.createBooks(bookBeans);
	}

	public String edit() {
		System.out.println("MainBean.edit()");
		return "edit";
	}

	public String addBooks() {
		System.out.println("MainBean.addBooks()");
		return "addBooks";
	}
	
	public String addBook() {
		System.out.println("MainBean.addBook()");
		bookBeans.add(new BookBean(
				addBookTitle, addAuthorFirstName, addAuthorLastName));
		clearAddInfo();
		return "success";
	}
	
	private void clearAddInfo() {
		addBookTitle = "";
		addAuthorFirstName = "";
		addAuthorLastName = "";
	}

	public String cancel() {
		System.out.println("MainBean.cancel()");
		return "success";
	}
	
	public boolean getHaveBooksToUpdate() {
		System.out.println("MainBean.getHaveBooksToUpdate()");
		return bookBeans.size() > 0 && 
			(haveBooksToDelete() || haveBooksToAdd());
	}

	private boolean haveBooksToDelete() {
		for (BookBean bookBean : bookBeans) {
			if (bookBean.isMarkedForDeletion()) {
				return true;
			}
		}
		return false;
	}

	private boolean haveBooksToAdd() {
		for (BookBean bookBean : bookBeans) {
			if (bookBean.isMarkedForAddition()) {
				return true;
			}
		}
		return false;
	}

	public String getAddBookTitle() {
		return addBookTitle;
	}

	public void setAddBookTitle(String title) {
		addBookTitle = title;
	}

	public String getAddAuthorFirstName() {
		return addAuthorFirstName;
	}

	public void setAddAuthorFirstName(String name) {
		addAuthorFirstName = name;
	}

	public String getAddAuthorLastName() {
		return addAuthorLastName;
	}

	public void setAddAuthorLastName(String name) {
		addAuthorLastName = name;
	}

	public String logout() {
		clear();
		userBean.logout();
		return "logout";
	}
	
	private void clear() {
		library = null;
		bookBeans = null;		
	}
	
	public String doAction() {
		return null;
	}
	
}
