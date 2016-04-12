/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.frontend;

import core.FrontendServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MyPC
 */
public class IndexServlet extends FrontendServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("about".equals(action)) {
            this.actionAbout(request, response);
        } else {
            this.actionIndex(request, response);
        }
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "Home");
            Date date = new Date();
            DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
            request.setAttribute("today", df.format(date));
            this.include("site/index.jsp", request, response);
        } catch (ServletException | IOException | SQLException ex) {
            this.error(500, "Something went wrong", request, response);
        }
    }

    private void actionAbout(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "About US");
            this.include("site/about.jsp", request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
