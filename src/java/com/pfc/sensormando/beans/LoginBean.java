/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.hibernate.Helper;
import com.pfc.sensormando.hibernate.Usuarios;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    @ManagedProperty(value = "#{userControllerBean}")
    private UserControllerBean userControllerBean;
    private Helper helper;
    @Size(min = 1)
    private String name;
    @Size(min = 1)
    private String password;

    public LoginBean() {
        helper = new Helper();
    }

    public String login() {
        Usuarios user = null;
        if (name != null && password != null) {
            user = helper.compruebaLogin(name, password);
        }
        if (user != null) {
            userControllerBean.setUsuario(user);
        }
        return "login";
    }

    public void logout() {
        userControllerBean.setUsuario(null);
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setUserControllerBean(UserControllerBean userControllerBean) {
        this.userControllerBean = userControllerBean;
    }
}
