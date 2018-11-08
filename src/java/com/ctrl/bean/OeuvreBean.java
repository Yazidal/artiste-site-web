/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctrl.bean;


import com.dao.OeuvreDAO;
import com.pojos.Oeuvres;
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
@ManagedBean(name = "oeuvreBean")
@SessionScoped
public class OeuvreBean {

    private OeuvreDAO oDao = new OeuvreDAO();  
    private Oeuvres o = new Oeuvres(); 
    private List < Oeuvres > olist; 
    private UploadedFile uf;

   

      public OeuvreBean() {
    }

//----------Getters and Setters (DAO+Bean)---------//
    public OeuvreDAO getoDao() {
        return oDao;
    }

    public void setoDao(OeuvreDAO oDao) {
        this.oDao = oDao;
    }

    public Oeuvres getO() {
        return o;
    }

    public void setO(Oeuvres o) {
        this.o = o;
    }

    public List<Oeuvres> getOlist() {
         olist =  oDao.AllOeuvres();
        return olist;
    }

    public void setOlist(List<Oeuvres> olist) {
        this.olist = olist;
    }
    
    public UploadedFile getUf() {
        return uf;
    }

    public void setUf(UploadedFile uf) {
        this.uf = uf;
    }

  //------------CRUD methods----------------//

    public void addUser() throws FileNotFoundException  
    {  
        try{
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                String filename = uf.getFileName();
                File file = new File("C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\galerie\\"+filename);//Script marche bien, il faut juste changer le Path C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\galerie\\
                bis = new BufferedInputStream(uf.getInputstream());
                FileOutputStream fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                int x;
                while((x = bis.read())!= -1){
                    bos.write(x);
                }
            } catch (IOException ex) {
                Logger.getLogger(Oeuvres.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    bos.flush();
                    bos.close();
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Oeuvres.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
                int e = (Integer)session.getAttribute("Id_artiste");
                System.out.println("Id de lartiste est : "+e);
                o.setIdFk(e);
                o.setCode(oDao.getCode());
                String filename = uf.getFileName();
                o.setToile(filename);
                oDao.add(o);  
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Oeuvre successfully saved.");  
                RequestContext.getCurrentInstance().showMessageInDialog(message);  
                o = new Oeuvres();  

        }catch(Exception e){
            e.printStackTrace(System.out);
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", " Oeuvre not saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message); 
        }  
       
    }  
    public void changeOeuvre(Oeuvres o)  
    {  
        this.o = o;  
    }  
    public void updateOeuvre()  
    {  
        try{      
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                String filename = uf.getFileName();
                File file = new File("C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\galerie\\"+filename);//Script marche bien, il faut juste changer le Path C:\\Users\\yazid\\OneDrive\\Documents\\NetBeansProjects\\MohamedAbaoubida\\web\\resources\\images\\galerie\\
                bis = new BufferedInputStream(uf.getInputstream());
                FileOutputStream fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                int x;
                while((x = bis.read())!= -1){
                    bos.write(x);
                }
            } catch (IOException ex) {
                Logger.getLogger(Oeuvres.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    bos.flush();
                    bos.close();
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Oeuvres.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String fileName = uf.getFileName();
          
            System.out.println("nom du file: "+fileName);
            o.setToile(fileName);
            oDao.update(o);  
            System.out.println("Oeuvre Info successfully saved.");  
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Oeuvre updated successfully .");  
            RequestContext.getCurrentInstance().showMessageInDialog(message);  
            o = new Oeuvres(); 
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
         
    }  
    public void deleteOeuvre(Oeuvres oe)  
    {   
        try{
            System.out.println(oe.getId());
            oDao.delete(oe);  
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
            RequestContext.getCurrentInstance().showMessageInDialog(message);  
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  
}
