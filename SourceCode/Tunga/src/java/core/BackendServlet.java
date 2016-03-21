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
import utility.Helper;

/**
 *
 * @author MyPC
 */
public class BackendServlet extends HttpServlet {

    public EntityModel em = new EntityModel();

    public void setTitle(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
    }

    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("login") != (Object) 1) {
            response.sendRedirect(Helper.baseUrl() + "/admin/login");
        }
    }

    public boolean isPost(HttpServletRequest request) {
        return "POST".equals(request.getMethod());
    }

    public void include(String viewPath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("themeUrl", Helper.baseUrl() + "/backend");
        RequestDispatcher rd = request.getRequestDispatcher("../backend/views/" + viewPath);
        rd.include(request, response);
    }

    public void setActiveSidebar(HttpServletRequest request, String route) {
        String[] routes = route.split("/");
        String servlet = routes[0];
        String method = routes[1];
        request.setAttribute("route", route);
        request.setAttribute("servlet", servlet);
        request.setAttribute("method", method);
    }
}
