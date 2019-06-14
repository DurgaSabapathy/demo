package com.chainsys.jdbc.test;

import java.sql.SQLException;
import java.util.Scanner;

import com.chainsys.jdbc.Book;
import com.chainsys.jdbc.BookDAO;
import com.chainsys.jdbc.BookValidator;

public class TestBookDAO {

	public static void main(String[] args) throws Exception {
		BookDAO bookDAO = new BookDAO();
		Book book = new Book();
		Scanner scanner = new Scanner(System.in);
		BookValidator bookValidator = new BookValidator();
		int choice;
		System.out
				.println("1.ADD BOOK DETAILS \n2.UPDATE BOOK DETAILS\n3.DELETE BOOK DETAILS\n4.FIND BOOK BY ID\n5.DISPLAY ALL BOOKS");
		System.out.println("Enter your choice:");
		choice = scanner.nextInt();
		switch (choice) {
		case 1:

			System.out.println("Enter id,name,price,publish date to add");

			book.setName(scanner.next());
			book.setPrice(scanner.nextFloat());
			book.setPublisheddate(scanner.next());

	

			bookDAO.addBook(book);

			bookDAO.findAll();

			break;
		case 2:
			System.out.println("Enter id,name to update");
			int id1 = scanner.nextInt();
			String name1 = scanner.next();
			bookValidator.validateBookAfterInsert(name1);
			bookDAO.updateBook(id1, name1);
			bookDAO.findAll();

			break;
		case 3:
			System.out.println("Enter id to delete");
			 book.setId(scanner.nextInt());
bookValidator.deleteValidator(book.getId());
			bookDAO.deleteBook(book);
			bookDAO.findAll();
			break;

		case 4:
			System.out.println("Enter id to search");
			book.setId(scanner.nextInt());
			bookValidator.deleteValidator(book.getId());
			Book b=bookDAO.findById(book);

			break;
		case 5:

			bookDAO.findAll();

			break;
		}

	}
}
