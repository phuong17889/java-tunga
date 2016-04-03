/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.backend;

import core.BackendServlet;
import entity.Invoice;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.InvoiceModel;

/**
 *
 * @author Hoangha.FPT
 */
public class OrderServlet extends BackendServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            this.checkLogin(request, response);
            String action = request.getParameter("action");
            switch (action) {
                case "index":
                    this.actionIndex(request, response);
                    break;
                case "view":
                    this.actionView(request, response);
                    break;
                case "delete":
                    this.actionDelete(request, response);
                    break;
                case "update":
                    this.actionUpdate(request, response);
                    break;
                default:
                    this.actionIndex(request, response);
                    break;
            }
        } catch (IOException ex) {
            this.error(404, "Page Not Found", request, response);
        }
    }

    private void actionUpdate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        Invoice m = InvoiceModel.find(id);
        if (m != null) {
            try {
                m.setStatus(status);
                HttpSession session = request.getSession();
                if (InvoiceModel.update(id, m)) {
                    session.setAttribute("message", "Updated!");
                } else {
                    session.setAttribute("message", "Something went wrong!");
                }
                response.sendRedirect("order?action=view&id=" + id);
            } catch (IOException ex) {
                this.error(404, "Page Not Found", request, response);
            }
        } else {
            this.error(404, "Page Not Found", request, response);
        }
    }

    private void actionView(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "View an existing order");
            this.setActiveSidebar(request, "order/index");
            int id = Integer.parseInt(request.getParameter("id"));
            Invoice m = InvoiceModel.find(id);
            if (m == null) {
                this.error(404, "Page Not Found", request, response);
            }
            request.setAttribute("order", m);
            this.include("order/view.jsp", request, response);
        } catch (ServletException | IOException ex) {
            this.error(404, "Page Not Found", request, response);
        }
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Invoice m = InvoiceModel.find(id);
        if (m != null && InvoiceModel.delete(id)) {
            request.setAttribute("message", "success");
        } else {
            request.setAttribute("message", "error");
        }
        this.actionIndex(request, response);
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "List order");
            this.setActiveSidebar(request, "order/index");
            String date = request.getParameter("date");
            List<Invoice> list;
            if (date != null) {
                String datetime[] = date.split(" - ");
                request.setAttribute("dateRanger", date);
                request.setAttribute("startDate", datetime[0]);
                request.setAttribute("toDate", datetime[1]);
                String startDate[] = datetime[0].split("/");
                String fromDate = startDate[2] + "-" + startDate[0] + "-" + startDate[1];
                String endDate[] = datetime[1].split("/");
                String toDate = endDate[2] + "-" + endDate[0] + "-" + endDate[1];
                list = InvoiceModel.findAll("WHERE createdAt >= '" + fromDate + " 00:00:00' AND createdAt <= '" + toDate + " 23:59:59'");
            } else {
                Date dt = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                request.setAttribute("dateRanger", "01/01/2016 - " + sdf.format(dt));
                request.setAttribute("startDate", "01/01/2016");
                request.setAttribute("toDate", sdf.format(dt));
                list = InvoiceModel.findAll();
            }
            request.setAttribute("invoices", list);
            this.include("order/index.jsp", request, response);
        } catch (ServletException | IOException ex) {
            this.error(404, "Page Not Found", request, response);
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
