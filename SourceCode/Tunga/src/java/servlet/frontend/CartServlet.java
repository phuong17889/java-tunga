/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.frontend;

import core.FrontendServlet;
import entity.Food;
import entity.Invoice;
import entity.InvoiceFood;
import entity.InvoiceTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FoodModel;
import model.InvoiceFoodModel;
import model.InvoiceModel;
import model.InvoiceTableModel;
import utility.Helper;

/**
 *
 * @author Hoangha.FPT
 */
public class CartServlet extends FrontendServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "submit":
                    this.actionSubmit(request, response);
                    break;
                case "view":
                    this.actionView(request, response);
                    break;
                case "delete":
                    this.actionDelete(request, response);
                    break;
                case "deleteTable":
                    this.actionDeleteTable(request, response);
                    break;
            }
        } catch (NullPointerException e) {
            this.error(500, "Something went wrong", request, response);
        }
    }

    private void actionView(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "Your shopping cart");
            HttpSession session = request.getSession();
            request.setAttribute("cart", session.getAttribute("cart"));
            request.setAttribute("reserve", session.getAttribute("reserve"));
            this.include("cart/view.jsp", request, response);
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

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) {
        if (this.isPost(request)) {
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            InvoiceFood cart = (InvoiceFood) session.getAttribute("cart");
            InvoiceTable reserve = (InvoiceTable) session.getAttribute("reserve");
            cart.deleteCartFood(id);
            if (cart.getCartFood().isEmpty()) {
                session.removeAttribute("cart");
            } else {
                session.setAttribute("cart", cart);
            }
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                int countTotal = (int) cart.getTotalCount() + (session.getAttribute("reserve") != null ? 1 : 0);
                float total = (float) cart.getTotalPrice() + (session.getAttribute("reserve") != null ? reserve.getPrice() : 0);
                out.print("{\"isEmpty\": \"" + (cart.getCartFood().isEmpty() && session.getAttribute("reserve") == null) + "\", \"count\": \"" + countTotal + "\", \"totalText\": \"" + Helper.currency(total) + "\", \"total\": \"" + total + "\"}");
            } catch (IOException | SQLException ex) {
                this.error(500, "Something went wrong", request, response);
            }
        } else {
            this.error(500, "Something went wrong", request, response);
        }
    }

    private void actionDeleteTable(HttpServletRequest request, HttpServletResponse response) {
        if (this.isPost(request)) {
            HttpSession session = request.getSession();
            InvoiceFood cart = (InvoiceFood) session.getAttribute("cart");
            if (session.getAttribute("reserve") != null) {
                session.removeAttribute("reserve");
            }
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"isEmpty\": \"" + cart.getCartFood().isEmpty() + "\", \"count\": \"" + cart.getTotalCount() + "\", \"totalText\": \"" + Helper.currency(cart.getTotalPrice()) + "\", \"total\": \"" + cart.getTotalPrice() + "\"}");
            } catch (IOException | SQLException ex) {
                this.error(500, "Something went wrong", request, response);
            }
        } else {
            this.error(500, "Something went wrong", request, response);
        }
    }

    private void actionSubmit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (this.isPost(request)) {
            try {
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                String token = Helper.random();
                float total = 0;
                int status = Invoice.STATUS_PENDING;
                int notify = Invoice.NOTIFY_PENDING;
                Invoice in = new Invoice(fullName, email, address, phone, total, token, status, notify);
                if (session.getAttribute("reserve") != null) {
                    InvoiceTable reserve = (InvoiceTable) session.getAttribute("reserve");
                    if (InvoiceTableModel.checkStatus(reserve)) {
                        session.setAttribute("errorTable", "This table has been booked by someone before, please choose another!");
                        response.sendRedirect("cart?action=view");
                    }
                }
                if (InvoiceModel.insert(in)) {
                    if (session.getAttribute("reserve") != null) {
                        InvoiceTable reserve = (InvoiceTable) session.getAttribute("reserve");
                        total += reserve.getPrice();
                        reserve.setInvoiceId(in.getId());
                        if (InvoiceTableModel.insert(reserve)) {
                            session.removeAttribute("reserve");
                        }
                    }
                    if (session.getAttribute("cart") != null) {
                        InvoiceFood cart = (InvoiceFood) session.getAttribute("cart");
                        total += cart.getTotalPrice();
                        for (Map.Entry<Integer, Integer> entry : cart.getCartFood().entrySet()) {
                            int key = entry.getKey();
                            Food f = FoodModel.find(key);
                            int quantity = entry.getValue();
                            InvoiceFood ifd = new InvoiceFood(in.getId(), f.getId(), quantity);
                            InvoiceFoodModel.insert(ifd);
                        }
                        session.removeAttribute("cart");
                    }
                    in.setTotal(total);
                    InvoiceModel.update(in.getId(), in);
                }
                response.sendRedirect("order?action=view&token=" + token);
            } catch (IOException | SQLException ex) {
                this.error(500, "Something went wrong", request, response);
            }
        } else {
            try {
                session.setAttribute("error", "Something went wrong!");
                response.sendRedirect("cart?action=view");
            } catch (IOException ex) {
                this.error(500, "Something went wrong", request, response);
            }
        }
    }
}
