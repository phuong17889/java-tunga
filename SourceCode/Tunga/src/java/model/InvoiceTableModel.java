/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import static core.EntityModel.em;
import entity.Food;
import entity.InvoiceTable;
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
 * @author MyPC
 */
public class InvoiceTableModel extends EntityModel {

    public static List<InvoiceTable> findAll(String condition) {
        List<InvoiceTable> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceTable " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int invoiceId = rs.getInt("invoiceId");
                int tableId = rs.getInt("tableId");
                float price = rs.getInt("price");
                String fromTime = rs.getString("fromTime");
                String toTime = rs.getString("toTime");
                InvoiceTable it = new InvoiceTable(id, invoiceId, tableId, price, fromTime, toTime);
                list.add(it);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }

    public static int countAllByInvoice(String condition) {
        String sql = "SELECT DISTINCT invoice.* FROM invoiceTable " + condition;
        int count = 0;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                count++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static boolean insert(InvoiceTable it) {
        int result = 0;
        try {
            String sql = "INSERT INTO invoiceTable (invoiceId, tableId, price, fromTime, toTime) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prst.setInt(1, it.getInvoiceId());
            prst.setInt(2, it.getTableId());
            prst.setFloat(3, it.getPrice());
            prst.setString(4, it.getFromTime());
            prst.setString(5, it.getToTime());
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                it.setId(rs.getInt(1));
            }
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static InvoiceTable find(String condition) {
        InvoiceTable it = null;
        String sql = "SELECT * FROM invoiceTable " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                it = new InvoiceTable(rs.getInt("id"), rs.getInt("invoiceId"), rs.getInt("tableId"), rs.getFloat("price"), rs.getString("fromTime"), rs.getString("toTime"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return it;
    }
}
