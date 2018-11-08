/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctrl.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
/**
 *
 * @author yazid
 */
@ManagedBean(name = "mailingBean")
@SessionScoped
public class MailingBean implements Serializable{

    /**
     * Creates a new instance of MailingBean
     */
    private String to;
    private String from;
    private String subject;
    private String bodyText;
    
    public MailingBean() {
    }

    //----------Getters and Setters---------//
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
    //------------methods----------------//
    
    
    public void send() 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String email = (String)session.getAttribute("email_artiste");
        try{ 
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");	
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            try{
            Session sess = Session.getInstance(props,new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(email, "mohamedabana18");
                }
            });
                Message message = new MimeMessage(sess);
                message.setFrom(new InternetAddress(this.from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("gallery med");
                message.setText(this.bodyText);
                Transport.send(message);
                FacesMessage mess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Email envoyé !");  
                RequestContext.getCurrentInstance().showMessageInDialog(mess); 
                System.out.println(this.to);
                System.out.println(this.from);
                System.out.println(this.subject);
                System.out.println(this.bodyText);
                this.from=null;
                this.to=null;
                this.subject=null;
                this.bodyText=null;
            } catch (MessagingException e){
                FacesMessage mess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Un probleme est survenu !");  
                RequestContext.getCurrentInstance().showMessageInDialog(mess); 
                throw new RuntimeException(e);
            }
        }
        catch (Exception e){
            FacesMessage mess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Un probleme est survenu !");  
            RequestContext.getCurrentInstance().showMessageInDialog(mess); 
            throw new RuntimeException(e);
        }
    }
    
}
