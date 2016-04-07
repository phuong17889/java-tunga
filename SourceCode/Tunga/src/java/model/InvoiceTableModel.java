/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import static core.EntityModel.em;
import entity.InvoiceTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyPC
 */
public class InvoiceTableModel extends EntityModel {

    public static List<InvoiceTable> findAll(String condition) throws SQLException {
        List<InvoiceTable> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceTable " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
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
        }
        return list.size() > 0 ? list : null;
    }

    public static int countAllByInvoice(String condition) throws SQLException {
        String sql = "SELECT DISTINCT invoice.* FROM invoiceTable " + condition;
        int count = 0;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                count++;
            }
        }
        return count;
    }

    public static boolean insert(InvoiceTable it) throws SQLException {
        int result;
        String sql = "INSERT INTO invoiceTable (invoiceId, tableId, price, fromTime, toTime) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
        }
        return result > 0;
    }

    public static InvoiceTable find(String condition) throws SQLException {
        InvoiceTable it = null;
        String sql = "SELECT * FROM invoiceTable " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                it = new InvoiceTable(rs.getInt("id"), rs.getInt("invoiceId"), rs.getInt("tableId"), rs.getFloat("price"), rs.getString("fromTime"), rs.getString("toTime"));
            }
        }
        return it;
    }

    public static boolean checkStatus(InvoiceTable it) throws SQLException {
        boolean existed;
        String sql = "SELECT * FROM invoiceTable WHERE tableId = " + it.getTableId() + " AND ((fromTime <= '" + it.getFromTime() + "' AND toTime >= '" + it.getFromTime() + "') OR (fromTime <= '" + it.getToTime() + "' AND toTime >= '" + it.getToTime() + "'))";
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            existed = rs.next();
        }
        return existed;
    }
}
