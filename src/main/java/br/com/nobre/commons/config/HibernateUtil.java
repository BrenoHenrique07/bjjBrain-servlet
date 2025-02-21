package br.com.nobre.commons.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {

        	Configuration configuration = new Configuration(); 
        	configuration = configuration.configure("hibernate.cfg.xml");
        	configuration = configuration.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
        	configuration = configuration.setProperty("hibernate.connection.username", System.getenv("DB_USER"));
        	configuration = configuration.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
        	
        	sessionFactory = configuration.buildSessionFactory();
        	
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
            
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
