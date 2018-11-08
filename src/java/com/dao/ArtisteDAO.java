/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.pojos.Artiste;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author yazid
 */
public class ArtisteDAO {
    
      private List < Artiste > DaoAllArtiste;  

    public List < Artiste > AllArtiste()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllArtiste = session.createCriteria(Artiste.class).list();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllArtiste;  
    } 
     public void update(Artiste ex)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(ex);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
     
      public Artiste getRow()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {  
            String hql = "from Artiste";  
            Query query = session.createQuery(hql);  
            return (Artiste) query.uniqueResult();
        }
        catch(Exception e)
        {
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }
       finally  
        {  
            session.close();  
        }  
     
        return null; 
                
  } 
    public  Artiste  SearchByRecordNo(String l,String p)  
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  

        try  
        {  
            session.beginTransaction();  
           
            Query qu = session.createQuery("From Artiste U where U.login =:login and U.pass =:pass");
            qu.setParameter("login", l);  
            qu.setParameter("pass", p); 
            
            return (Artiste) qu.uniqueResult();

        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        finally  
        {  
            session.close();  
        }  
     
        return null;  
    }  
}
