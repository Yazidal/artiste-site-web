/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.pojos.Expos;
import java.util.List;
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import com.util.HibernateUtil;   
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
/**
 *
 * @author yazid
 */
public class ExpoDAO {

    private List < Expos > DaoAllExpos;  
  
    public List < Expos > AllExpos()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllExpos = session.createCriteria(Expos.class).list();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllExpos;  
    }  
    public List < Expos > cart()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            Criteria criteria = session.createCriteria(Expos.class);
            criteria.addOrder(Order.desc("date"));
            DaoAllExpos = criteria.list();
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllExpos;  
    } 
    public void add(Expos ex)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {   
            session.beginTransaction();  
            session.merge(ex);  
            session.flush();  
            System.out.println("NewExposition saved, id: " +  
                ex.getId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Expos ex)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.delete(ex);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Expos ex)  
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
}
