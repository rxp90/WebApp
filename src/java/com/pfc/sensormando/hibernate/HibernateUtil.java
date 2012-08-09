/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Sobremesa
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.configure("hibernate.cfg.xml");
            cfg.addAnnotatedClass(com.pfc.sensormando.hibernate.Usuarios.class);
            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
