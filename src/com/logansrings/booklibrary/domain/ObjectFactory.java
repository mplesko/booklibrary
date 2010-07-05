package com.logansrings.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import com.logansrings.booklibrary.bean.AuthorBean;
import com.logansrings.booklibrary.bean.BookBean;

public class ObjectFactory {

	public static List<BookBean> createBookBeans(Library library) {
		return createBookBeans(library.getBooks());
	}

	public static List<BookBean> createBookBeans(List<Book> books) {
		List<BookBean> bookBeans = new ArrayList<BookBean>();
		for (Book book : books) {
			bookBeans.add(createBookBean(book));
		}
		return bookBeans;
	}

	public static BookBean createBookBean(Book book) {
		return new BookBean(book.getId(), book.getTitle(), 
				book.getAuthorId(), book.getAuthorFirstName(), book.getAuthorLastName());
	}

	public static List<Book> createBooks(List<BookBean> bookBeans) {
		List<Book> books = new ArrayList<Book>();
		for (BookBean bookBean : bookBeans) {
			books.add(createBook(bookBean));
		}
		return books;
	}

	private static Book createBook(BookBean bookBean) {
		return new Book(bookBean.getTitle(), 
				new Author(bookBean.getAuthorFirstName(), 
						bookBean.getAuthorLastName()));
	}

	public static List<AuthorBean> createAuthorBeans(List<Author> authors) {
		List<AuthorBean> authorBeans = new ArrayList<AuthorBean>();
		for (Author author : authors) {
			authorBeans.add(createAuthorBean(author));
		}
		return authorBeans;
	}

	private static AuthorBean createAuthorBean(Author author) {
		return new AuthorBean(author.getFirstName(), author.getLastName());
	}


}
