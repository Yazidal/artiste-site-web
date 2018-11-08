/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.pojos.Visites;
import com.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author yazid
 */
public class VisitesDAO {
    
    private List < Visites > DaoAllVisites;  
    
    public List < Visites > AllVisites()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllVisites = session.createCriteria(Visites.class).list();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllVisites;  
    }  
     
     public void add(Visites vi)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {   
            session.beginTransaction();  
            session.merge(vi);  
            session.flush();  
            System.out.println("NewExposition saved, id: " +  
                vi.getId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
     public void update(Visites vi)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(vi);  
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
     public void delete(Visites vi)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.delete(vi);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public List<Visites> visitejour()
    {
         try{
            Session session = HibernateUtil.getSessionFactory().openSession();  
            String hq = "from Visites V where V.date = :date";  
            String d = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            Query query = session.createQuery(hq);  
            query.setString("date", d);  
            DaoAllVisites =query.list();
            return DaoAllVisites;
         }catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
         return null;
    }
    public List<Visites> recordvisitejour()
    {
         try{
            Session session = HibernateUtil.getSessionFactory().openSession();  
            String hq = "from Visites V order by V.visitesJour desc";  
            Query query = session.createQuery(hq);  
            DaoAllVisites =query.list();
            return DaoAllVisites;
         }catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
         return null;
    }
     public Integer totalvisites()
    {
         try{
            Session session = HibernateUtil.getSessionFactory().openSession();  
            String hq = "select sum(V.visitesJour) from Visites V ";  
            Query query = session.createQuery(hq);  
            List results = query.list();  
            int nb =Integer.parseInt(results.get(0).toString());
            return nb;
         }catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
         return null;
    }
     public Double moyvisites()
    {
         try{
            Session session = HibernateUtil.getSessionFactory().openSession();  
            String hq = "select avg(V.visitesJour) from Visites V ";  
            Query query = session.createQuery(hq);  
            List results = query.list();  
            double nb =Double.parseDouble(results.get(0).toString());
            return nb;
         }catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
         return null;
    }
    
}
