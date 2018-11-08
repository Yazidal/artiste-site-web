/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yazid
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req1=(HttpServletRequest)request;
        HttpServletResponse res1=(HttpServletResponse)response;
        
        String sessionUser=(String)req1.getSession().getAttribute("username");
        String currentPath=req1.getRequestURL().toString();
        
        if(sessionUser!=null){
            if(currentPath.contains("MohamedAbaoubida/faces/logmed.xhtml")){
                res1.sendRedirect(req1.getContextPath()+"crud/oeuvreCrud.xhtml");
            }else{
                chain.doFilter(request, response);
            }
        }else{
            if(currentPath.contains("crud")){
                res1.sendRedirect(req1.getContextPath()+"/faces/logmed.xhtml");
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
   
}
