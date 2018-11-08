/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctrl.bean;

import com.dao.VisitesDAO;
import com.pojos.Visites;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yazid
 */
@ManagedBean(name = "visitesBean")
@SessionScoped
public class VisitesBean {

    /**
     * Creates a new instance of VisitesBean
     */
    private VisitesDAO vDAO = new VisitesDAO();
    private Visites v = new Visites(); 
    private List < Visites > vlist; 
    private String moy;
    private String sum;
    private String date;
    private String nb_parjr;
    private String record;

//------------- getters and setters ------------//

    public VisitesDAO getvDAO() {
        return vDAO;
    }

    public void setvDAO(VisitesDAO vDAO) {
        this.vDAO = vDAO;
    }
    
    public Visites getV() {
        return v;
    }

    public void setV(Visites v) {
        this.v = v;
    }

    public List<Visites> getVlist() {
          vlist = vDAO.AllVisites();
        return vlist;
    }

    public void setVlist(List<Visites> vlist) {
        this.vlist = vlist;
    }

    public String getMoy() {
        return moy;
    }

    public void setMoy(String moy) {
        this.moy = moy;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNb_parjr() {
        return nb_parjr;
    }

    public void setNb_parjr(String nb_parjr) {
        this.nb_parjr = nb_parjr;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
    
    //-------------------- Methodes ------------------------//
    public VisitesBean() {
    }
    
    public void totalvisites()
    {
        int nb=vDAO.totalvisites();
        sum=Integer.toString(nb);
        System.out.println(nb+" est le total des visites");
    }
     public void moyvisites()
    {
        double nb=vDAO.moyvisites();
        moy=Double.toString(nb);
        System.out.println(nb+" est la moyenne des visites");
    }
    public void visites_jour(ServletRequest request) throws ParseException
    {
        HttpServletRequest req1=(HttpServletRequest)request;
        vlist = vDAO.visitejour();
        String currentPath=req1.getRequestURL().toString();
        
        if(vlist.isEmpty())
        {
            String d = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
             if(!currentPath.contains("crud")){
                v.setDate(format.parse(d));
                v.setVisitesJour(1);
                vDAO.add(v);
             }
            nb_parjr="1";
            date=d;
        }
        else{
             if(currentPath.contains("crud")){
                v.setId(vlist.get(0).getId());
                v.setVisitesJour(vlist.get(0).getVisitesJour());
                v.setDate(vlist.get(0).getDate());
                Integer t=v.getVisitesJour();
                nb_parjr=t.toString();
                date=v.getDate().toString();
             }
             else{
                System.out.println(vlist.get(0).getVisitesJour());
                v.setId(vlist.get(0).getId());
                v.setVisitesJour(vlist.get(0).getVisitesJour()+1);
                v.setDate(vlist.get(0).getDate());
                System.out.println(v.getVisitesJour());
                vDAO.update(v);
                Integer t=v.getVisitesJour();

                nb_parjr=t.toString();
                date=v.getDate().toString();
             }
        }
    }
    public void record_visite_jour()
    {
        vlist=vDAO.recordvisitejour();
        System.out.println(vlist.get(0).getVisitesJour());
        Integer t=vlist.get(0).getVisitesJour();
        record=t.toString();
    }
}
