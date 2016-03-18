/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.Model;
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
public class InvoiceModel extends Model {
    public boolean insert(Invoice in) {
        int result = 0;
        try {
            String sql = "INSERT INTO invoice (full_name, address, phone, tax, total, token, status, creat_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setString(1, in.getFullName());
            prst.setString(2, in.getAddress());
            prst.setString(3, in.getPhone());
            prst.setFloat(4, in.getTax());
            prst.setFloat(5, in.getTotal());
            prst.setString(6, in.getToken());
            prst.setBoolean(7, in.isStatus());
            prst.setString(8, in.getCreateAt());
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }
     public boolean update(int id, Invoice in) {
        int result = 0;
        String sql = "UPDATE invoice set full_name = ?, address = ?, phone = ?, tax = ?, total=?, status=?, creat_at=? where id = ?";
        try {
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setString(1, in.getFullName());
            prst.setString(2, in.getAddress());
            prst.setString(3, in.getPhone());
            prst.setFloat(4, in.getTax());
            prst.setFloat(5, in.getTotal());
            prst.setBoolean(6, in.isStatus());
            prst.setString(7, in.getCreateAt());
            prst.setInt(8, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }
     public List<Invoice> findAll() {
        List<Invoice> list = new ArrayList<Invoice>();
        String sql = "SELECT * FROM invoice";
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String full_name = rs.getString("full_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float tax = rs.getFloat("tax");
                float total= rs.getFloat("total");
                String token= rs.getString("token");
                Boolean status= rs.getBoolean("status");
                String creat_at= rs.getString("creat_at");
                Invoice in = new Invoice(id, full_name, address, phone, tax, total, token, true, creat_at);
                list.add(in);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<Invoice> findAll(String condition) {
        List<Invoice> list = new ArrayList<Invoice>();
        String sql = "SELECT * FROM invoice WHERE " + condition;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String full_name = rs.getString("full_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float tax = rs.getFloat("tax");
                float total= rs.getFloat("total");
                String token= rs.getString("token");
                Boolean status= rs.getBoolean("status");
                String creat_at= rs.getString("creat_at");
                Invoice in = new Invoice(id, full_name, address, phone, tax, total, token, true, creat_at);
                list.add(in);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public Invoice find(int id) {
        Invoice in = null;
        String sql = "SELECT * FROM invoice id = " + id;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                in = new Invoice(rs.getInt("id"), rs.getString("full_name"), rs.getString("address"), rs.getString("phone"), rs.getFloat("tax"), rs.getFloat("total"), rs.getString("token"), rs.getBoolean("status"), rs.getString("created_at"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }
     public Invoice find(String condition) {
        Invoice in = null;
        String sql = "SELECT * FROM invoice WHERE " + condition;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                in = new Invoice(rs.getInt("id"), rs.getString("full_name"), rs.getString("address"), rs.getString("phone"), rs.getFloat("tax"), rs.getFloat("total"), rs.getString("token"), rs.getBoolean("status"), rs.getString("created_at"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }
     public boolean delete(int id) {
        int result = 0;
        String sql = "DELETE FROM invoice WHERE id = ?";
        try {
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setInt(1, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }
     public boolean delete(String condition) {
        int result = 0;
        String sql = "DELETE FROM invoice WHERE " + condition;
        try {
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

}
