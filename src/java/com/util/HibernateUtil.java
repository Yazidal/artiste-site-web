/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
  

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author yazid
 */
public class HibernateUtil {
        private static SessionFactory sessionFactory;  
    private static SessionFactory buildSessionFactory()  
    {  
        try  
        {  
           Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
            applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build()); 
            return sessionFactory;  
        }  
        catch (Throwable ex)  
        {  
            // Make sure you log the exception, as it might be swallowed  
            System.err.println("Initial SessionFactory creation failed." + ex);  
            throw new ExceptionInInitializerError(ex);  
        }  
    }  
    public static SessionFactory getSessionFactory()  
    {  
        if (sessionFactory == null) sessionFactory = buildSessionFactory();  
        return sessionFactory;  
    }  
    public static void shutdown()  
    {  
        // Close caches and connection pools  
        sessionFactory.close();  
    }  
}
