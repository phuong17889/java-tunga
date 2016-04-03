/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.frontend;

import core.FrontendServlet;
import entity.Book;
import entity.InvoiceTable;
import entity.Room;
import entity.Table;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.RoomModel;
import utility.Helper;

/**
 *
 * @author MyPC
 */
public class TableServlet extends FrontendServlet {

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
                case "list":
                    this.actionList(request, response);
                    break;
                case "book":
                    this.actionBook(request, response);
                    break;
            }
        } catch (NullPointerException e) {
            this.error(500, "Something went wrong", request, response);
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

    private void actionBook(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (session.getAttribute("book") != null) {
                Book book = (Book) session.getAttribute("book");
                Table valid = null;
                List<Room> rooms = RoomModel.findAll();
                for (Room room : rooms) {
                    List<Table> tables = room.getFreeTables(book);
                    if (tables != null) {
                        for (Table table : tables) {
                            if (id == table.getId()) {
                                valid = table;
                                break;
                            }
                        }
                        if (valid != null) {
                            break;
                        }
                    }
                }
                if (valid != null) {
                    DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
                    Date date = df.parse(book.getDate() + " " + book.getTime());
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    InvoiceTable reserve = new InvoiceTable();
                    reserve.setPrice(valid.getPrice());
                    reserve.setTableId(valid.getId());
                    reserve.setFromTime(dt.format(date));
                    reserve.setToTime(dt.format(Helper.calcToTime(book.getDate() + " " + book.getTime())));
                    session.setAttribute("reserve", reserve);
                    session.removeAttribute("book");
                    response.sendRedirect("cart?action=view");
                } else {
                    this.error(500, "Something went wrong", request, response);
                }
            } else {
                this.error(500, "Something went wrong", request, response);
            }
        } catch (NumberFormatException | ParseException | IOException e) {
            this.error(500, "Something went wrong", request, response);
        }
    }

    private void actionList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        this.setTitle(request, "Table booking");
        if (this.isPost(request)) {
            try {
                int number = Integer.parseInt(request.getParameter("number"));
                String date = request.getParameter("date");
                String time = request.getParameter("time");
                Book book = new Book(number, date, time);
                session.setAttribute("book", book);
                response.sendRedirect("table?action=list");
            } catch (IOException ex) {
                this.error(500, "Something went wrong", request, response);
            }
        }
        if (session.getAttribute("book") != null) {
            try {
                List<Room> rooms = RoomModel.findAll();
                request.setAttribute("rooms", rooms);
                request.setAttribute("sessionId", session.getId());
                this.include("table/list.jsp", request, response);
            } catch (ServletException | IOException ex) {
                this.error(404, "Page Not Found", request, response);
            }
        } else {
            this.error(404, "Page Not Found", request, response);
        }
    }

}
