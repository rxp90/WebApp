/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.hibernate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
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

                res = true;

                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.INFO, "Usuario {0} insertado", usuario);

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                //session.close(); No es necesario con getCurrentSession();
            }

        }
        return res;
    }

    public Usuarios compruebaLogin(String name, String password) {
        Usuarios res = null;

        if (session != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //do some work

                String queryStr = "from Usuarios s where s.username = :searchName and s.password = :searchPassword";
                List<Usuarios> result = session.createQuery(queryStr)
                        .setString("searchName", name).setString("searchPassword", password).list();

                tx.commit();

                if (result.size() > 0) {
                    res = result.get(0);
                    Logger logger = Logger.getLogger("Helper");
                    logger.log(Level.INFO, "Usuario {0} comprobado", res);
                }

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                //session.close(); No es necesario con getCurrentSession();
            }

        }

        return res;
    }

    public String recuperarPassword(String email) {
        String password = null;
        if (session != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //do some work

                String queryStr = "from Usuarios s where s.email = :searchEmail";
                List<Usuarios> result = session.createQuery(queryStr)
                        .setString("searchEmail", email).list();

                tx.commit();

                if (result.size() > 0) {
                    Usuarios usuario = result.get(0);
                    password = usuario.getPassword();
                    Logger logger = Logger.getLogger("Helper");
                    logger.log(Level.INFO, "Recuperada la contraseña del usuario: {0}", password);
                }

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                //session.close(); No es necesario con getCurrentSession();
            }
        }
        return password;
    }

    public boolean actualizaUsuario(Usuarios usuario) {
        boolean res = false;
        if (session != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //do some work
                session.merge(usuario);
                tx.commit();

                res = true;

                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.INFO, "Usuario {0} modificado", usuario);

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                //session.close(); No es necesario con getCurrentSession();
            }
        }
        return res;
    }
}
