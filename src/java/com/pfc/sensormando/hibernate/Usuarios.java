package com.pfc.sensormando.hibernate;
// Generated 09-ago-2012 11:31:47 by Hibernate Tools 3.2.1.GA

import org.hibernate.validator.constraints.Email;




/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {

     private String username;
     private String password;
     private String nombre;
     private String email;
     private char baja;
     private char administrador;

    public Usuarios() {
    }

    public Usuarios(String username, String password, String nombre, String email, char baja, char administrador) {
       this.username = username;
       this.password = password;
       this.nombre = nombre;
       this.email = email;
       this.baja = baja;
       this.administrador = administrador;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public char getBaja() {
        return this.baja;
    }
    
    public void setBaja(char baja) {
        this.baja = baja;
    }
    public char getAdministrador() {
        return this.administrador;
    }
    
    public void setAdministrador(char administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "username=" + username + ", password=" + password + ", nombre=" + nombre + ", email=" + email + ", baja=" + baja + ", administrador=" + administrador + '}';
    }

}


