/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.hibernate.Helper;
import com.pfc.sensormando.hibernate.Usuarios;
import com.pfc.sensormando.utilidades.EnviarEmail;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class RegistroBean {

    private Helper helper;
    @Size(min = 1)
    private String user;
    @Size(min = 1)
    private String password;
    @Size(min = 1)
    private String nombre;
    @Email
    private String email;
    @NotNull
    private boolean baja;
    @NotNull
    private boolean administrador;

    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {
        helper = new Helper();
    }

    public String getUser() {
        return user;
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

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String redirige() {
        return "registro";
    }

    public String registrarse() {
        String res = null;
        char bajaChar = 'n';
        char adminChar = 'n';

        if (baja) {
            bajaChar = 'y';
        }
        if (administrador) {
            adminChar = 'y';
        }

        helper.crearUsuario(new Usuarios(user, password, nombre, email, bajaChar, adminChar));
        
        EnviarEmail emailSender = new EnviarEmail();
        emailSender.setDestinatario(email);
        emailSender.setAsunto("Cuenta creada en Sensor Mando");
        
        StringBuilder cuerpo = new StringBuilder("Enhorabuena. Un administrador te ha creado una cuenta para la aplicación Sensor Mando. Aquí tienes tus datos para iniciar sesión.\n\n\tNombre de usuario: ");
        cuerpo.append(user);
        cuerpo.append("\n\tContraseña: ");
        cuerpo.append(password);
        
        emailSender.setCuerpo(cuerpo.toString());
        emailSender.sendEmail();
        
        return "login";
    }
}
