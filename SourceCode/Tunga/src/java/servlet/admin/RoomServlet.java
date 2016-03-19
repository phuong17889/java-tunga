/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import core.AdminServlet;
import entity.Room;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RoomModel;

/**
 *
 * @author TuanDo
 */
public class RoomServlet extends AdminServlet {

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
        this.setTitle(request, "Add a new room");
        this.setActiveSidebar(request, "room/add");
        if (this.isPost(request)) {
            String name = request.getParameter("name");
            boolean type = (Integer.parseInt(request.getParameter("type")) == 1);
            Room r = new Room(name, type);
            if (RoomModel.insert(r)) {
                request.setAttribute("message", "success");
            } else {
                request.setAttribute("message", "failed");
            }
        }
        this.include("views/room/add.jsp", request, response);
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.setTitle(request, "List room");
        this.setActiveSidebar(request, "room/index");
        List<Room> list = RoomModel.findAll();
        request.setAttribute("rooms", list);
        this.include("views/room/index.jsp", request, response);
    }

    private void actionView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.setTitle(request, "View an existing room");
        this.setActiveSidebar(request, "room/index");
        int id = Integer.parseInt(request.getParameter("id"));
        Room r = RoomModel.find(id);
        if (r == null) {
            throw new ServletException("Not found");
        }
        request.setAttribute("room", r);
        this.include("views/room/view.jsp", request, response);
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Room r = RoomModel.find(id);
        if (r != null && RoomModel.delete(id)) {
            request.setAttribute("message", "success");
        } else {
            request.setAttribute("message", "error");
        }
        this.actionIndex(request, response);
    }

    private void actionEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.setTitle(request, "Edit a room");
        this.setActiveSidebar(request, "room/index");
        int id = Integer.parseInt(request.getParameter("id"));
        Room r = RoomModel.find(id);
        if (this.isPost(request) && r != null) {
            String name = request.getParameter("name");
            boolean type = (Integer.parseInt(request.getParameter("type")) == 1);
            r.setName(name);
            r.setType(type);
            if (RoomModel.update(id, r)) {
                request.setAttribute("message", "success");
            } else {
                request.setAttribute("message", "error");
            }
        }
        request.setAttribute("room", r);
        this.include("views/room/edit.jsp", request, response);
    }

}
