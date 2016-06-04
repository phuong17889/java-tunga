/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.backend;

import core.BackendServlet;
import entity.Invoice;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.InvoiceFoodModel;
import model.InvoiceModel;
import model.InvoiceTableModel;
import utility.Helper;

/**
 *
 * @author MyPC
 */
public class IndexServlet extends BackendServlet {

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
        if (request.getSession().getAttribute("login") != (Object) 1) {
            try {
                response.sendRedirect(Helper.baseUrl() + "/admin/login");
            } catch (IOException ex) {
                this.error(404, "Page Not Found", request, response);
            }
        } else if (action != null) {
            switch (action) {
                case "newOrder":
                    this.actionNewOrder(request, response);
                    break;
                case "logout":
                    this.actionLogout(request, response);
                    break;
                case "index":
                    this.actionIndex(request, response);
                    break;
                default:
                    this.actionIndex(request, response);
                    break;
            }
        } else {
            this.actionIndex(request, response);
        }
    }

    private void actionNewOrder(HttpServletRequest request, HttpServletResponse response) {
        if (this.isPost(request)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Invoice invoice = InvoiceModel.find(id);
                request.setAttribute("invoice", invoice);
                this.include("site/_order.jsp", request, response);
            } catch (ServletException | IOException | SQLException ex) {
                this.error(404, "Page Not Found", request, response);
            }
        } else {
            this.error(500, "Something Went Wrong", request, response);
        }
    }

    private void actionLogout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("login", 0);
            response.sendRedirect(Helper.baseUrl() + "/admin/login");
        } catch (IOException ex) {
            this.error(500, "Something went wrong", request, response);
        }
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "Dashboard");
            List<Invoice> list = InvoiceModel.findAll("ORDER BY createdAt DESC", 10);
            Calendar calendar = Calendar.getInstance();
            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            String[] categories = new String[days];
            String[] foodOrdered = new String[days];
            String[] tableBooked = new String[days];
            String year = calendar.get(Calendar.YEAR) + "";
            String month = (calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1) : "" + (calendar.get(Calendar.MONTH) + 1);
            for (int i = 1; i <= days; i++) {
                String j = i + "";
                if (i < 10) {
                    j = "0" + i;
                }
                categories[i - 1] = "'" + j + "'";
                int countFood = InvoiceFoodModel.countAllByInvoice("LEFT JOIN invoice ON invoice.id = invoiceFood.invoiceId WHERE CONVERT(VARCHAR(25), invoice.createdAt, 126) LIKE '" + year + "-" + month + "-" + j + "%'");
                foodOrdered[i - 1] = countFood + "";
                int countTable = InvoiceTableModel.countAllByInvoice("LEFT JOIN invoice ON invoice.id = invoiceTable.invoiceId WHERE CONVERT(VARCHAR(25), invoice.createdAt, 126) LIKE '" + year + "-" + month + "-" + j + "%'");
                tableBooked[i - 1] = countTable + "";
            }
            request.setAttribute("categories", Helper.implode(",", categories));
            request.setAttribute("foodOrdered", Helper.implode(",", foodOrdered));
            request.setAttribute("tableBooked", Helper.implode(",", tableBooked));
            request.setAttribute("list", list);
            this.include("site/index.jsp", request, response);
        } catch (ServletException | IOException | SQLException ex) {
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
