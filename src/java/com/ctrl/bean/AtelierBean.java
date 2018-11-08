/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctrl.bean;

import com.dao.AtelierDAO;
import com.pojos.Ateliers;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author yazid
 */
@ManagedBean(name = "atelierBean")
@SessionScoped
public class AtelierBean {

    private Ateliers a = new Ateliers();
    private AtelierDAO aDao = new AtelierDAO();
    private List<Ateliers> alist;
    private UploadedFile uf;
    private List < Ateliers > alist_cart; 

 
    
    public AtelierBean() {
    }
    
//----------Getters and Setters (DAO+Bean)---------//
    public Ateliers getA() {
        return a;
    }

    public void setA(Ateliers a) {
        this.a = a;
    }

    public AtelierDAO getaDao() {
        return aDao;
    }

    public void setaDao(AtelierDAO aDao) {
        this.aDao = aDao;
    }

    public List<Ateliers> getAlist() {
        alist=aDao.AllAteliers();
        return alist;
    }

    public void setAlist(List<Ateliers> alist) {
        this.alist = alist;
    }
    
    public UploadedFile getUf() {
        return uf;
    }

    public void setUf(UploadedFile uf) {
        this.uf = uf;
    }

    public List<Ateliers> getAlist_cart() {
        alist_cart= aDao.cart();
        return alist_cart;
    }

    public void setAlist_cart(List<Ateliers> alist_cart) {
        this.alist_cart = alist_cart;
    }
    
    
    //------------CRUD methods----------------//
    public void addAtelier() throws FileNotFoundException  
    {  
        try
        {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                String filename = uf.getFileName();
                File file = new File("C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\ateliers\\"+filename);//Script marche bien, il faut juste changer le Path C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\galerie\\
                bis = new BufferedInputStream(uf.getInputstream());
                FileOutputStream fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                int x;
                while((x = bis.read())!= -1){
                    bos.write(x);
                }
            } catch (IOException ex) {
                Logger.getLogger(Ateliers.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    bos.flush();
                    bos.close();
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Ateliers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            int ida = (Integer)session.getAttribute("Id_artiste");
            System.out.println("Id de lartiste est : "+ida);
            a.setIdFk(ida);
            String filename = uf.getFileName();
            a.setPhoto(filename);
            aDao.add(a);  
            System.out.println("Atelier successfully saved.");  
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Atelier successfully saved.");  
            RequestContext.getCurrentInstance().showMessageInDialog(message);  
            a = new Ateliers();  
        
        }catch(Exception e){
            e.printStackTrace(System.out);
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", " Oeuvre not saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message); 
        }  
    }  
    public void changeAtelier(Ateliers ex)  
    {  
        this.a = ex;  
    }  
    public void updateAtelier()  
    {   
        
        BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                String filename = uf.getFileName();
                File file = new File("C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\ateliers\\"+filename);//Script marche bien, il faut juste changer le Path C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\galerie\\
                bis = new BufferedInputStream(uf.getInputstream());
                FileOutputStream fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                int x;
                while((x = bis.read())!= -1){
                    bos.write(x);
                }
            } catch (IOException ex) {
                Logger.getLogger(Ateliers.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    bos.flush();
                    bos.close();
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Ateliers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        
        
        String fileName = uf.getFileName();
        System.out.println("nom de la photo: "+fileName);
        a.setPhoto(fileName);
        aDao.update(a);  
        System.out.println("Atelier Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Atelier updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        a = new Ateliers();  
    }  
    public void deleteAtelier(Ateliers ex)  
    {  
        aDao.delete(ex);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  

}
