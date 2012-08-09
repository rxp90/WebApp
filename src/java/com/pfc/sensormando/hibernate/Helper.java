/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.hibernate;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sobremesa
 */
public class Helper {

    Session session = null;

    public Helper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public boolean crearUsuario(Usuarios usuario) {
        boolean res = false;
        if (session != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //do some work
                session.save(usuario);
                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                //session.close(); No es necesario con getCurrentSession();
                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.INFO, "Usuario {0} insertado", usuario);
                res = true;
            }

        }
        return res;
    }
}
