/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import core.AdminServlet;
import entity.Table;
import entity.Room;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TableModel;
import model.RoomModel;

/**
 *
 * @author TuanDo
 */
public class TableServlet extends AdminServlet {

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
//        this.checkLogin(request, response);
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                this.actionAdd(request, response);
                break;
            case "index":
                this.actionIndex(request, response);
                break;
            case "edit":
                this.actionEdit(request, response);
                break;
            case "view":
                this.actionView(request, response);
                break;
            case "delete":
                this.actionDelete(request, response);
                break;
            default:
                this.actionIndex(request, response);
                break;
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

    private void actionAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.setTitle(request, "Add a new table");
        this.setActiveSidebar(request, "table/add");
        if (this.isPost(request)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            String name = request.getParameter("name");
            int type = Integer.parseInt(request.getParameter("type"));
            float price = Float.parseFloat(request.getParameter("price"));
            Table t = new Table(roomId, name, type, price);
            if (TableModel.insert(t)) {
                request.setAttribute("message", "success");
            } else {
                request.setAttribute("message", "failed");
            }
        }
        List<Room> list = RoomModel.findAll();
        request.setAttribute("rooms", list);
        this.include("views/table/add.jsp", request, response);
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.setTitle(request, "List Table");
        this.setActiveSidebar(request, "table/index");
        List<Table> list = TableModel.findAll();
        request.setAttribute("tables", list);
        this.include("views/table/index.jsp", request, response);
    }

    private void actionView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.setTitle(request, "View an existing table");
        this.setActiveSidebar(request, "table/index");
        int id = Integer.parseInt(request.getParameter("id"));
        Table t = TableModel.find(id);
        if (t == null) {
            throw new ServletException("Not found");
        }
        List<Room> list = RoomModel.findAll();
        request.setAttribute("rooms", list);
        request.setAttribute("table", t);
        this.include("views/table/view.jsp", request, response);
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Table t = TableModel.find(id);
        if (t != null && TableModel.delete(id)) {
            request.setAttribute("message", "success");
        } else {
            request.setAttribute("message", "error");
        }
        this.actionIndex(request, response);
    }

    private void actionEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.setTitle(request, "Edit a table");
        this.setActiveSidebar(request, "table/index");
        int id = Integer.parseInt(request.getParameter("id"));
        Table t = TableModel.find(id);
        if (this.isPost(request) && t != null) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            String name = request.getParameter("name");
            int type = Integer.parseInt(request.getParameter("type"));
            float price = Float.parseFloat(request.getParameter("price"));
            t.setName(name);
            t.setRoomId(roomId);
            t.setType(type);
            t.setPrice(price);
            if (TableModel.update(id, t)) {
                request.setAttribute("message", "success");
            } else {
                request.setAttribute("message", "error");
            }
        }
        List<Room> list = RoomModel.findAll();
        request.setAttribute("rooms", list);
        request.setAttribute("table", t);
        this.include("views/table/edit.jsp", request, response);
    }

}
