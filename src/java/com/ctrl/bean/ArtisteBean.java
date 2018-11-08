/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctrl.bean;

import com.dao.ArtisteDAO;
import com.pojos.Artiste;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yazid
 */
@ManagedBean(name = "artisteBean")
@SessionScoped
public class ArtisteBean implements Serializable{

    /**
     * Creates a new instance of ArtisteBean
     */
    private ArtisteDAO aDao = new ArtisteDAO();  
    private Artiste a = new Artiste(); 
    private Artiste a2 = new Artiste(); 
    private List<Artiste> alist;

 
    public ArtisteBean() {
    }
//------------ Getters and Setters (DAO+BEAN) ---------------//
    public ArtisteDAO getaDao() {
        return aDao;
    }

    public void setaDao(ArtisteDAO aDao) {
        this.aDao = aDao;
    }

    public Artiste getA() {
        return a;
    }

    public void setA(Artiste a) {
        this.a = a;
    }
    
    public Artiste getA2() {
        return a2;
    }

    public void setA2(Artiste a2) {
        this.a2 = a2;
    }

    public List<Artiste> getAlist() {
        alist=aDao.AllArtiste();
        return alist;
    }

    public void setAlist(List<Artiste> alist) {
        this.alist = alist;
    }
    
    
//---------------------method to check if login and pass exists in database----------------//
    public String searchbyRecordno() throws IOException  
    {  
       FacesMessage message = null;
        System.out.println(this.a.getLogin());
        System.out.println(this.a.getPass());
      
        if(aDao.SearchByRecordNo(this.a.getLogin(),this.a.getPass())!=null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", a.getLogin());
           return "crud/oeuvreCrud.xhtml?faces-redirect=true";
        }else{
      
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            this.a = new Artiste();
           
        }
        return null;
    }  
    public String bio()
    {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            String e4 = (String)session.getAttribute("Bio_artiste");
            return e4;
    }
    public void inf()
    {
        FacesMessage message = null;
        try{
            a2 = aDao.getRow();
            
            System.out.println(a2.getId());
            System.out.println(a2.getBio());
            System.out.println(a2.getEmail());
            System.out.println(a2.getNom());
            System.out.println(a2.getPrenom());
            System.out.println(a2.getTel());
            System.out.println(a2.getAdresse());
            System.out.println("_______________________________________");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Id_artiste",a2.getId());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email_artiste",a2.getEmail());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Nom_artiste", a2.getNom());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Prenom_artiste",a2.getPrenom());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Bio_artiste", a2.getBio());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Tel_artiste",a2.getTel());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Adresse_artiste",a2.getAdresse());
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            int e = (Integer)session.getAttribute("Id_artiste");
            String e1 = (String)session.getAttribute("email_artiste");
            String e2 = (String)session.getAttribute("Nom_artiste");
            String e3 = (String)session.getAttribute("Prenom_artiste");
            String e4 = (String)session.getAttribute("Bio_artiste");
            String e5 = (String)session.getAttribute("Tel_artiste");
            String e6 = (String)session.getAttribute("Adresse_artiste");
            System.out.println(e);
            System.out.println(e1);
            System.out.println(e2);
            System.out.println(e3);
            System.out.println(e4);
            System.out.println(e5);
            System.out.println(e6);

            
            System.out.println("info enregistré");
        }catch(Exception e)
        {
            System.out.println("info non enregistré");
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Connexion Error", "Pas de connexion, veuillez vous connecter");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        
    }
    
      public void changeArtiste(Artiste ex)  
    {  
        this.a = ex;  
    }  
    public void updateArtiste()  
    {   
        aDao.update(a);  
        System.out.println("Artiste Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Artiste updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        a = new Artiste();  
    }  
    
}
