/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import entity.Invoice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TuanDo
 */
public class InvoiceModel extends EntityModel {

    public static boolean insert(Invoice in) {
        int result = 0;
        try {
            String sql = "INSERT INTO invoice (fullName, email, address, phone, total, token, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prst = em.getConnection().prepareStatement(sql);
            prst.setString(1, in.getFullName());
            prst.setString(2, in.getEmail());
            prst.setString(3, in.getAddress());
            prst.setString(4, in.getPhone());
            prst.setFloat(5, in.getTotal());
            prst.setString(6, in.getToken());
            prst.setInt(7, in.getStatus());
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static boolean update(int id, Invoice in) {
        int result = 0;
        String sql = "UPDATE invoice SET fullName = ?, email = ?, address = ?, phone = ?, total = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement prst = em.getConnection().prepareStatement(sql);
            prst.setString(1, in.getFullName());
            prst.setString(1, in.getEmail());
            prst.setString(3, in.getAddress());
            prst.setString(4, in.getPhone());
            prst.setFloat(5, in.getTotal());
            prst.setInt(6, in.getStatus());
            prst.setInt(7, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static List<Invoice> findAll() {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice";
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float total = rs.getFloat("total");
                String token = rs.getString("token");
                int status = rs.getInt("status");
                String createdAt = rs.getString("createdAt");
                Invoice in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt);
                list.add(in);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Invoice> findAll(String condition) {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice WHERE " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float total = rs.getFloat("total");
                String token = rs.getString("token");
                int status = rs.getInt("status");
                String createdAt = rs.getString("createdAt");
                Invoice in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt);
                list.add(in);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }

    public static Invoice find(int id) {
        Invoice in = null;
        String sql = "SELECT * FROM invoice WHERE id = " + id;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float total = rs.getFloat("total");
                String token = rs.getString("token");
                int status = rs.getInt("status");
                String createdAt = rs.getString("createdAt");
                in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

    public static Invoice find(String condition) {
        Invoice in = null;
        String sql = "SELECT * FROM invoice WHERE " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float total = rs.getFloat("total");
                String token = rs.getString("token");
                int status = rs.getInt("status");
                String createdAt = rs.getString("createdAt");
                in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

    public static boolean delete(int id) {
        int result = 0;
        String sql = "DELETE FROM invoice WHERE id = ?";
        try {
            PreparedStatement prst = em.getConnection().prepareStatement(sql);
            prst.setInt(1, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static boolean delete(String condition) {
        int result = 0;
        String sql = "DELETE FROM invoice WHERE " + condition;
        try {
            PreparedStatement prst = em.getConnection().prepareStatement(sql);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }
}
