package com.hibernate.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hibernate.dao.BookDao;
import com.hibernate.pojo.Book;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = new Book("The Last Thing He Told Me","Laura Dave","Thriller",2021);
			BookDao dao = new BookDao();
			dao.addBook(book);			
			System.out.println("Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unsuccessfully");
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
