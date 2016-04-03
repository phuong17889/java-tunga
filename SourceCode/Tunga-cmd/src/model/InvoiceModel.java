/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Invoice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import utility.DataProcess;

/**
 *
 * @author TuanDo
 */
public class InvoiceModel {

    public static boolean update(int id, Invoice in) {
        int result = 0;
        String sql = "UPDATE invoice SET fullName = ?, email = ?, address = ?, phone = ?, total = ?, status = ?, notify = ? WHERE id = ?";
        try {
            PreparedStatement prst = DataProcess.getConnection().prepareStatement(sql);
            prst.setString(1, in.getFullName());
            prst.setString(2, in.getEmail());
            prst.setString(3, in.getAddress());
            prst.setString(4, in.getPhone());
            prst.setFloat(5, in.getTotal());
            prst.setInt(6, in.getStatus());
            prst.setInt(7, in.getNotify());
            prst.setInt(8, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static Invoice find(String condition) {
        Invoice in = null;
        String sql = "SELECT * FROM invoice " + condition;
        try {
            Statement st = DataProcess.getConnection().createStatement();
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
                int notify = rs.getInt("notify");
                in = new Invoice(id, fullName, email, address, phone, total, token, status, createdAt, notify);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

}
