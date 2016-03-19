/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import core.AdminServlet;
import entity.Food;
import entity.Menu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FoodModel;
import model.MenuModel;

/**
 *
 * @author TuanDo
 */
public class FoodServlet extends AdminServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getSession().getAttribute("login") != (Object) 1) {
            response.sendRedirect("login");
        } else {
            String action = request.getParameter("action");
            switch (action) {
                case "add":
                    this.actionAdd(request, response);
                    break;

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void actionAdd(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "Add a new fod");
            if ("POST".equals(request.getMethod())) {
                String name = request.getParameter("name");
                float price = Float.parseFloat(request.getParameter("price"));
                String image = request.getParameter("price");
                int menu_id = Integer.parseInt(request.getParameter("menu.id"));
                FoodModel fm = new FoodModel();
                Food f = new Food(menu_id, name, price, image);
                HttpSession session = request.getSession();
                if (fm.insert(f)) {
                    session.setAttribute("message", "OK");
                } else {
                    session.setAttribute("message", "FAILED");
                }
            }
            MenuModel mm = new MenuModel();
            List<Menu> list = mm.findAll();
            request.setAttribute("menus", list);
            RequestDispatcher rd = request.getRequestDispatcher("views/food/add.jsp");
            rd.include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FoodServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
