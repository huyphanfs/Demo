package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
	private static final SessionFactory factory;
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("SessionFactory creation failed" + e);
            throw new ExceptionInInitializerError(e);
		}
	}
	 public static SessionFactory getSessionFactory() {
		    return factory;
	    }
	
}
