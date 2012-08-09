/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.hibernate.Helper;
import com.pfc.sensormando.hibernate.Usuarios;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class RegistroBean {

    private Helper helper;
    private String user;
    private String password;
    private String nombre;
    @Email
    private String email;
    private char baja;
    private char administrador;

    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {
        helper = new Helper();
    }

    public String getUser() {
        return user;
    }

    public boolean isInputValid(String id) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent(id);
        return input.isValid();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getBaja() {
        return baja;
    }

    public void setBaja(char baja) {
        this.baja = baja;
    }

    public char getAdministrador() {
        return administrador;
    }

    public void setAdministrador(char administrador) {
        this.administrador = administrador;
    }

    public String redirige() {
        return "registro";
    }

    public String registrarse() {
        String res = null;
        helper.crearUsuario(new Usuarios(user, password, nombre, email, 'n', 'n'));
        return "login";
    }
}
