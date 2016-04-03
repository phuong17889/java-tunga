/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import entity.InvoiceFood;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuModel;
import utility.Helper;

/**
 *
 * @author MyPC
 */
public class FrontendServlet extends HttpServlet {

    public EntityModel em = new EntityModel();

    public void setTitle(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
    }

    public boolean isPost(HttpServletRequest request) {
        return "POST".equals(request.getMethod());
    }

    public void include(String viewPath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        InvoiceFood cart = (InvoiceFood) session.getAttribute("cart");
        request.setAttribute("cart", cart);
        request.setAttribute("reserve", session.getAttribute("reserve"));
        request.setAttribute("menus", MenuModel.findAll());
        request.setAttribute("themeUrl", Helper.baseUrl() + "/frontend");
        RequestDispatcher rd = request.getRequestDispatcher("frontend/views/" + viewPath);
        rd.include(request, response);
    }

    public void setActiveMenu(HttpServletRequest request, String route) {
        String[] routes = route.split("/");
        String servlet = routes[0];
        String method = routes[1];
        request.setAttribute("route", route);
        request.setAttribute("servlet", servlet);
        request.setAttribute("method", method);
    }

    public void error(int code, String message, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("code", code);
            request.setAttribute("message", message);
            this.include("site/error.jsp", request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(BackendServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
