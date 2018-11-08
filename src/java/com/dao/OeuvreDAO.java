/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.pojos.Oeuvres;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class OeuvreDAO {
   private List < Oeuvres > DaoAllOeuvres; 
    
   public List < Oeuvres > AllOeuvres()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllOeuvres = session.createCriteria(Oeuvres.class).list();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllOeuvres;  
    }  
   
    public Integer getId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from Oeuvres U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer userId = 1;  
        if (results.get(0) != null)  
        {  
            userId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return userId;  
    }  
    public String getCode()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hq0 = "from Oeuvres"; 
        String hql = "select U.code from Oeuvres U order by U.id desc";  
        Query query = session.createQuery(hql);  
        query.setFirstResult(0);
        List results = query.list();  
        String code;  
        String newcode=null;
        if (session.createQuery(hq0).list().isEmpty()){  
            newcode="A0";
        }
        else
        {
            code=(String) results.get(0);
            String carac=code.substring(0,1);
            char letter=carac.charAt(0);
            int num=Integer.parseInt(code.substring(1));
            if(num==99){
                if("Z".equals(carac)){
                    return null;
                }
                else{
                    num=0;
                    letter++;
                }
            } 
            else{
                num++;
            }
            String n=Integer.toString(num);
            newcode=letter+n;
        }
        session.flush();  
        session.close();  
        return newcode;  
    }  
    public List < Oeuvres > SearchByRecordNo(String RecordNo)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Oeuvres > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Oeuvres U where U.id =:id");  
            qu.setParameter("id", RecordNo);  
            daoSearchList = qu.list();  
            session.getTransaction().commit();  
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
        return daoSearchList;  
    }  
    public void add(Oeuvres newo)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.merge(newo);  
            session.flush();  
            System.out.println("NewOeuvre saved, id: " +  
                newo.getId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Oeuvres o)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.delete(o);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Oeuvres o)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(o);  
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
