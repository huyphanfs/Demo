package com.hibernate.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.hibernate.dao.BookDao;
import com.hibernate.pojo.Book;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet({"/BookServlet", "/BookServlet/insert", "/BookServlet/update",
	"/BookServlet/delete", "/BookServlet/reset", "/BookServlet/edit"})
public class BookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		Book book = null;
		if (url.contains("delete")) {
			delete(request, response);
			request.setAttribute("book", book);
		}else if (url.contains("edit")) {
			edit(request, response);
		}else if (url.contains("reset")) {
			book = new Book();
			request.setAttribute("book", book);
		}
		showList(request, response);	
		request.getRequestDispatcher("/book.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		Book book = null;
		if (url.contains("insert")) {
			insert(request, response);
		}else if(url.contains("delete")) {
			delete(request, response);
			request.setAttribute("book", book);
		}else if (url.contains("update")) {
			update(request, response);
		}else if (url.contains("reset")) {
			book = new Book();
			request.setAttribute("book", book);
		}
		showList(request, response);
		request.getRequestDispatcher("/book.jsp").forward(request, response);
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = new Book();
			BeanUtils.populate(book, request.getParameterMap());
			
			BookDao dao = new BookDao();
			dao.addBook(book);
			request.setAttribute("message", "Book inserted successfully!");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Book inserted unsuccessfully!");
		}
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			BookDao dao = new BookDao();
			Book book = dao.findByID(id);
			request.setAttribute("book", book);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Book edited unsuccessfully!");
		}
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			BookDao dao = new BookDao();
			dao.deleteBook(id);

			request.setAttribute("error", "Book deleted unsuccessfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Book deleted unsuccessfully!");
		}
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = new Book();
			BeanUtils.populate(book, request.getParameterMap());						
			BookDao dao = new BookDao();			
			dao.updateBook(book);
			request.setAttribute("message", "Book updated successfully!");
			request.setAttribute("book", book);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Book updated unsuccessfully!");
		}
	}
	
	protected void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			BookDao dao = new BookDao();
			List<Book> list = dao.showList();
			request.setAttribute("books", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Book showed unsuccessfully!");
		}
	}
	
	
}
