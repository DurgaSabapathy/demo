package com.chainsys.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
public class BookDAO {
	/**
	 * precondition id,name,price must be valid
	 * 
	 */
Book book=new Book();
ArrayList<Book> booklist=new ArrayList<Book>();
	public void addBook(Book book) throws Exception {
		try
		{
		Connection connection=ConnectionUtil.getConnection();
		System.out.println(connection);
		String sql = "insert into books(id,name,price,publish_date)values(book_id_seq.nextval,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
		preparedStatement.setString(1,book.getName());

		preparedStatement.setFloat(2, book.getPrice());
		preparedStatement.setDate(3, Date.valueOf(book.getPublishdate()));
		

		int rows1 = preparedStatement.executeUpdate();
		System.out.println(rows1 + " row affected");
		ConnectionUtil.close(connection, preparedStatement, null);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		throw new Exception("unable to insert");	
	
		}
	}

	public void updateBook(int id, String name) throws Exception {
		try
		{
			
		
		Connection connection=ConnectionUtil.getConnection();
		String sql1 = "update books set name=? where id=?";

		PreparedStatement preparedStatement = connection.prepareStatement(sql1);

		preparedStatement.setString(1, name);
		
		preparedStatement.setInt(2, id);
		
		int rows1 = preparedStatement.executeUpdate();
		System.out.println(rows1 + "row updated");
		ConnectionUtil.close(connection, preparedStatement, null);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("unable to update");	
		}

	}

	public void deleteBook(Book book) throws Exception {
		try
		{
			
		
		Connection connection=ConnectionUtil.getConnection();
		String sql1 = "delete from books where id=?";

		PreparedStatement preparedStatement = connection.prepareStatement(sql1);

		preparedStatement.setInt(1, book.getId());
		int rows1 = preparedStatement.executeUpdate();
		System.out.println(rows1 + "row updated");
		ConnectionUtil.close(connection, preparedStatement, null);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("unable to delete");
		}
	}

	public ArrayList<Book> findAll() throws Exception {
		try
		{
		Connection connection=ConnectionUtil.getConnection();
		System.out.println("\n\n*******BOOK DETAILS************");
		String sql4 = "select id,name,price,publish_date from books order by name asc";

		PreparedStatement preparedStatement = connection.prepareStatement(sql4);

		System.out.println("ID\tNAME\tPRICE\tPUBLISHED DATE");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
Book book=new Book();
		book.setId(resultSet.getInt("id"));
		book.setName(resultSet.getString("name"));
		book.setPrice(resultSet.getFloat("price"));
	 book.setPublishdate(resultSet.getDate("publish_date").toLocalDate());
	
		booklist.add(book);
		}
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("unable to search");
		}
		return booklist;
		}
	
		
	

	public Book findById(Book book) throws Exception {
		try
		{
		Connection connection=ConnectionUtil.getConnection();
		String sql3 = "select id,name,price,publish_date from books where id=?";

		System.out.println(sql3);
		PreparedStatement preparedStatement = connection.prepareStatement(sql3);

		preparedStatement.setInt(1, book.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
book=null;
		if (resultSet.next()) {
			book=new Book();
			book.setId(resultSet.getInt("id"));
			book.setName(resultSet.getString("name"));
			book.setPrice(resultSet.getFloat("price"));
		 book.setPublishdate(resultSet.getDate("publish_date").toLocalDate());
		
		
			System.out.println("ID\tNAME\tPRICE\tPUBLISHED DATE");
			System.out.println(resultSet.getInt("id") + "\t"
					+ resultSet.getString("name") + "\t"
					+ resultSet.getFloat("price")+"\t"+resultSet.getDate("publish_date").toLocalDate());
		} else {
			System.out.println("\nNo record found");
		}
		ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("unable to search");
		}
		return book;
		
	}
	
}
