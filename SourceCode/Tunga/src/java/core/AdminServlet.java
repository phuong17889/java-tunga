/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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
