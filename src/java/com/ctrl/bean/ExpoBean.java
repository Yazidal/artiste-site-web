/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctrl.bean;


import com.dao.ExpoDAO;
import com.pojos.Expos;
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
@ManagedBean(name = "expoBean")
@SessionScoped
public class ExpoBean {

    private ExpoDAO oDao = new ExpoDAO();  
    private Expos e = new Expos(); 
    private List < Expos > elist; 
    private List < Expos > elist_cart; 
    
    public ExpoBean() {
    }
    
//----------Getters and Setters (DAO+Bean)---------//
    public ExpoDAO getoDao() {
        return oDao;
    }

    public void setoDao(ExpoDAO oDao) {
        this.oDao = oDao;
    }

    public Expos getE() {
        return e;
    }

    public void setE(Expos e) {
        this.e = e;
    }

    public List<Expos> getElist() {
        elist=oDao.AllExpos();
        return elist;
    }

    public void setElist(List<Expos> elist) {
        this.elist = elist;
    }

    public List<Expos> getElist_cart() {
        elist_cart=oDao.cart();
        return elist_cart;
    }

    public void setElist_cart(List<Expos> elist_cart) {
        this.elist_cart = elist_cart;
    }
    
    
    
//------------CRUD methods----------------//
    public void addExpos()  
    {  
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        int ida = (Integer)session.getAttribute("Id_artiste");
        System.out.println("Id de lartiste est : "+ida);
        e.setIdFk(ida);
        oDao.add(e);  
        System.out.println("Exposition successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Exposition successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        e = new Expos();  
    }  
    public void changeExpos(Expos ex)  
    {  
        this.e = ex;  
    }  
    public void updateExpos()  
    {   
        oDao.update(e);  
        System.out.println("Exposition Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Exposition updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        e = new Expos();  
    }  
    public void deleteExpos(Expos ex)  
    {  
        oDao.delete(ex);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    
}
