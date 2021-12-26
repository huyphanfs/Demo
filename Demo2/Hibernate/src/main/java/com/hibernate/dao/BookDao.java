package com.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.pojo.Book;
import com.hibernate.utils.HibernateUtils;

public class BookDao {
	public void addBook(Book book) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
			session.save(book);
			transaction.commit();		
			session.close();
	}
	
	public List<Book> showList(){
		List<Book> list = new ArrayList<Book>();
		Session session = HibernateUtils.getSessionFactory().openSession();
		list = session.createQuery("From Book").getResultList();
		return list;
	}
	
	public void updateBook(Book book) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(book);		
		transaction.commit();
		session.close();
	}
	
	
	public void deleteBook(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			Book book = session.find(Book.class, id);
			if(book!=null) {
				session.remove(book);
			}else {
				throw new Exception("Book cannot be found");
			}transaction.commit();			
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
		}
	}

	public Book findByID(int id) {
		Book book = new Book();
		Session session = HibernateUtils.getSessionFactory().openSession();
		book = session.find(Book.class, id);
		return book;
	}
	
	
}
