/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.hibernate.Helper;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.*;
import javax.mail.internet.*;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class RecuperarPasswordBean {

    private static final int EMAIL_ENVIADO = 0;
    private static final int EMAIL_INEXISTENTE_BD = 1;
    private static final int ERROR_ENVIANDO = -1;
    final String miCorreo = "sensormando@gmail.com";
    final String miContraseña = "UMtbWkj4";
    final String servidorSMTP = "smtp.gmail.com";
    final String puertoEnvio = "465";
    final String asunto = "Recuperación contraseña Sensor Mando";
    private String cuerpo = null;
    private Helper helper;
    @Email
    private String email;
    private int emailEnviado = -2;

    /**
     * Creates a new instance of RecuperarPasswordBean
     */
    public RecuperarPasswordBean() {
        helper = new Helper();
    }

    public void sendEmail() {

        Properties props = new Properties();
        props.put("mail.smtp.user", miCorreo);
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", puertoEnvio);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", puertoEnvio);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new autentificadorSMTP();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(cuerpo);
            msg.setSubject(asunto);
            msg.setFrom(new InternetAddress(miCorreo));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    email));
            Transport.send(msg);
            emailEnviado = EMAIL_ENVIADO;
        } catch (Exception mex) {
            emailEnviado = ERROR_ENVIANDO;

            Logger logger = Logger.getLogger("RecuperarPasswordBean");
            logger.log(Level.WARNING, "Excepci\u00f3n enviando e-mail: {0}", mex.getMessage());
        }

    }

    private class autentificadorSMTP extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(miCorreo, miContraseña);
        }
    }

    public void recuperarPassword() {
        if (email != null) {
            String existingPassword = helper.recuperarPassword(email);
            if (existingPassword != null) {
                cuerpo = "Estás recibiendo este email porque solicistate recordar tu contraseña del servicio Sensor Mando. Password: " + existingPassword;
                sendEmail();
            } else {
                emailEnviado = EMAIL_INEXISTENTE_BD;
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailEnviado() {
        return emailEnviado;
    }

    public void setEmailEnviado(int emailEnviado) {
        this.emailEnviado = emailEnviado;
    }
}
