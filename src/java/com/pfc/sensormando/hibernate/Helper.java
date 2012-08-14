/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Helper implements Serializable {

    Session session = null;

    public Helper() {
        /*
         * http://stackoverflow.com/questions/2378572/hibernate-session-is-closed
         this.session = HibernateUtil.getSessionFactory().getCurrentSession();
         * Ahora tengo que hacer close explícitamente.
         */

        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public boolean crearUsuario(Usuarios usuario) {
        this.session = HibernateUtil.getSessionFactory().openSession();

        boolean res = false;
        if (session != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //do some work
                session.save(usuario);
                tx.commit(); // Flush automático

                res = true;

                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.INFO, "Usuario {0} insertado", usuario);

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
            } finally {
                session.close();
            }

        }
        return res;
    }
    public boolean eliminar(Usuarios usuario) {
        this.session = HibernateUtil.getSessionFactory().openSession();

        boolean res = false;
        if (session != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //do some work
                session.delete(usuario);
                tx.commit(); // Flush automático

                res = true;

                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.INFO, "Usuario {0} borrado", usuario);

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
            } finally {
                session.close();
            }

        }
        return res;
    }

    public Usuarios compruebaLogin(String name, String password) {
        Usuarios res = null;
        this.session = HibernateUtil.getSessionFactory().openSession();

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
                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
            } finally {
                session.close();
            }

        }

        return res;
    }

    public String recuperarPassword(String email) {
        this.session = HibernateUtil.getSessionFactory().openSession();

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
                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
            } finally {
                session.close();
            }
        }
        return password;
    }

    public boolean actualizaUsuario(Usuarios usuario) {
        this.session = HibernateUtil.getSessionFactory().openSession();

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
                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
            } finally {
                session.close();
            }
        }
        return res;
    }

    public List<Usuarios> getUsuarios(Usuarios user) {

        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
        if (session != null) {
            try {
                Query query = session.createQuery("from Usuarios s where s.username!=:adminUsername");
                query.setParameter("adminUsername", user.getUsername());

                listaUsuarios = query.list();

                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.INFO, "Recogida lista de usuarios {0}", listaUsuarios.toString());
            } catch (Exception e) {
                Logger logger = Logger.getLogger("Helper");
                logger.log(Level.SEVERE, "Error en la consulta de usuarios {0}", e.getMessage());
            } finally {
                session.close();
            }
        }
        return listaUsuarios;
    }
}
