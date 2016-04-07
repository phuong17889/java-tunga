/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import static core.EntityModel.em;
import entity.InvoiceFood;
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
public class InvoiceFoodModel extends EntityModel {

    public static boolean insert(InvoiceFood ifd) throws SQLException {
        int result;
        String sql = "INSERT INTO invoiceFood (invoiceId, foodId, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prst.setInt(1, ifd.getInvoiceId());
            prst.setInt(2, ifd.getFoodId());
            prst.setInt(3, ifd.getQuantity());
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                ifd.setId(rs.getInt(1));
            }
        }
        return result > 0;
    }

    public static List<InvoiceFood> findAll(String condition) throws SQLException {
        List<InvoiceFood> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceFood " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int invoiceId = rs.getInt("invoiceId");
                int foodId = rs.getInt("foodId");
                int quantity = rs.getInt("quantity");
                InvoiceFood ifd = new InvoiceFood(id, invoiceId, foodId, quantity);
                list.add(ifd);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static int countAllByInvoice(String condition) throws SQLException {
        String sql = "SELECT DISTINCT invoice.* FROM invoiceFood " + condition;
        int count = 0;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                count++;
            }
        }
        return count;
    }
}
