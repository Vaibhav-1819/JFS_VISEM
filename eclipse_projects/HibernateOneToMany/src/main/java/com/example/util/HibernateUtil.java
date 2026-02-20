package com.example.util;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.*;
public class HibernateUtil {
	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	public static SessionFactory getFactory() {
		return factory;
	}
}
