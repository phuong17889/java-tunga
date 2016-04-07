/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.backend;

import core.BackendServlet;
import entity.Table;
import entity.Room;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.TableModel;
import model.RoomModel;
import utility.Helper;

/**
 *
 * @author TuanDo
 */
@MultipartConfig
public class TableServlet extends BackendServlet {

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
            if (action == null) {
                this.actionIndex(request, response);
            } else {
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
        } catch (IOException ex) {
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

    private String uploadFile(String column, HttpServletRequest request) {
        try {
            String path = Helper.appPath() + "uploads/tables";
            Part imagePart = request.getPart(column);
            String imageName = getFileName(imagePart);
            if (imageName != null) {
                OutputStream out;
                InputStream fileContent;
                try {
                    out = new FileOutputStream(new File(path + File.separator + imageName));
                    fileContent = imagePart.getInputStream();
                    int read;
                    byte[] bytes = new byte[1024];
                    while ((read = fileContent.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                } catch (Exception e) {
                    return null;
                }
            }
            return imageName;
        } catch (IOException | ServletException ex) {
            return "";
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void actionAdd(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "Add a new table");
            this.setActiveSidebar(request, "table/add");
            if (this.isPost(request)) {
                int roomId = Integer.parseInt(request.getParameter("roomId"));
                String name = request.getParameter("name");
                int type = Integer.parseInt(request.getParameter("type"));
                float price = Float.parseFloat(request.getParameter("price"));
                String description = request.getParameter("description");
                String imageName = this.uploadFile("image", request);
                Table t = new Table(roomId, name, description, imageName, type, price);
                if (TableModel.insert(t)) {
                    request.setAttribute("message", "success");
                } else {
                    request.setAttribute("message", "failed");
                }
            }
            List<Room> list = RoomModel.findAll();
            request.setAttribute("rooms", list);
            this.include("table/add.jsp", request, response);
        } catch (ServletException | IOException | SQLException ex) {
            this.error(404, "Page Not Found", request, response);
        }
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "List Table");
            this.setActiveSidebar(request, "table/index");
            List<Table> list = TableModel.findAll();
            request.setAttribute("tables", list);
            this.include("table/index.jsp", request, response);
        } catch (ServletException | IOException | SQLException ex) {
            this.error(404, "Page Not Found", request, response);
        }
    }

    private void actionView(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "View an existing table");
            this.setActiveSidebar(request, "table/index");
            int id = Integer.parseInt(request.getParameter("id"));
            Table t = TableModel.find(id);
            if (t == null) {
                this.error(404, "Page Not Found", request, response);
            }
            List<Room> list = RoomModel.findAll();
            request.setAttribute("rooms", list);
            request.setAttribute("table", t);
            this.include("table/view.jsp", request, response);
        } catch (ServletException | IOException | SQLException ex) {
            this.error(404, "Page Not Found", request, response);
        }
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Table t = TableModel.find(id);
            if (t != null && TableModel.delete(id)) {
                request.setAttribute("message", "success");
            } else {
                request.setAttribute("message", "error");
            }
            this.actionIndex(request, response);
        } catch (SQLException ex) {
            this.error(404, "Page Not Found", request, response);
        }
    }

    private void actionEdit(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setTitle(request, "Edit a table");
            this.setActiveSidebar(request, "table/index");
            int id = Integer.parseInt(request.getParameter("id"));
            Table t = TableModel.find(id);
            if (this.isPost(request) && t != null) {
                int roomId = Integer.parseInt(request.getParameter("roomId"));
                String name = request.getParameter("name");
                int type = Integer.parseInt(request.getParameter("type"));
                float price = Float.parseFloat(request.getParameter("price"));
                String description = request.getParameter("description");
                String imageName = this.uploadFile("image", request);
                t.setName(name);
                t.setRoomId(roomId);
                t.setType(type);
                t.setPrice(price);
                t.setDescription(description);
                t.setImage(imageName);
                if (TableModel.update(id, t)) {
                    request.setAttribute("message", "success");
                } else {
                    request.setAttribute("message", "error");
                }
            }
            List<Room> list = RoomModel.findAll();
            request.setAttribute("rooms", list);
            request.setAttribute("table", t);
            this.include("table/edit.jsp", request, response);
        } catch (ServletException | IOException | SQLException ex) {
            this.error(404, "Page Not Found", request, response);
        }
    }

}
