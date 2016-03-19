/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MyPC
 */
public class AdminServlet extends HttpServlet {

    public String[] breadcrumbs;
    public String servlet;
    public String method;

    public void setTitle(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
    }

    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("login") != (Object) 1) {
            response.sendRedirect("login");
        }
    }

    public boolean isPost(HttpServletRequest request) {
        return "POST".equals(request.getMethod());
    }
    
    public void include(String viewPath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher rd = request.getRequestDispatcher(viewPath);
        rd.include(request, response);
    }

    public String getServlet() {
        return servlet;
    }

    public void setServlet(String servlet) {
        this.servlet = servlet;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
