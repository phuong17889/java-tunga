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

/**
 *
 * @author TuanDo
 */
public class InvoiceModel extends EntityModel {

    public static boolean insert(Invoice in) throws SQLException {
        int result;
        String sql = "INSERT INTO invoice (fullName, email, address, phone, total, token, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, in.getFullName());
            prst.setString(2, in.getEmail());
            prst.setString(3, in.getAddress());
            prst.setString(4, in.getPhone());
            prst.setFloat(5, in.getTotal());
            prst.setString(6, in.getToken());
            prst.setInt(7, in.getStatus());
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                in.setId(rs.getInt(1));
            }
        }
        return result > 0;
    }

    public static boolean update(int id, Invoice in) throws SQLException {
        int result;
        String sql = "UPDATE invoice SET fullName = ?, email = ?, address = ?, phone = ?, total = ?, status = ?, notify = ? WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setString(1, in.getFullName());
            prst.setString(2, in.getEmail());
            prst.setString(3, in.getAddress());
            prst.setString(4, in.getPhone());
            prst.setFloat(5, in.getTotal());
            prst.setInt(6, in.getStatus());
            prst.setInt(7, in.getNotify());
            prst.setInt(8, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static List<Invoice> findAll() throws SQLException {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice";
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
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
                int notify = rs.getInt("notify");
                Invoice in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt, notify);
                list.add(in);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Invoice> findAll(String condition) throws SQLException {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
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
                int notify = rs.getInt("notify");
                Invoice in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt, notify);
                list.add(in);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Invoice> findAll(String condition, int limit) throws SQLException {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT TOP " + limit + " * FROM invoice " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
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
                int notify = rs.getInt("notify");
                Invoice in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt, notify);
                list.add(in);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static Invoice find(int id) throws SQLException {
        Invoice in = null;
        String sql = "SELECT * FROM invoice WHERE id = " + id;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float total = rs.getFloat("total");
                String token = rs.getString("token");
                int status = rs.getInt("status");
                String createdAt = rs.getString("createdAt");
                int notify = rs.getInt("notify");
                in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt, notify);
            }
        }
        return in;
    }

    public static Invoice find(String condition) throws SQLException {
        Invoice in = null;
        String sql = "SELECT * FROM invoice " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
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
                int notify = rs.getInt("notify");
                in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt, notify);
            }
        }
        return in;
    }

    public static boolean delete(int id) throws SQLException {
        int result;
        String sql = "DELETE FROM invoice WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setInt(1, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static boolean delete(String condition) throws SQLException {
        String sql = "DELETE FROM invoice " + condition;
        int result;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            result = prst.executeUpdate();
        }
        return result > 0;
    }
}
