/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import static core.EntityModel.em;
import entity.Invoice;
import entity.InvoiceFood;
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
public class InvoiceFoodModel extends EntityModel {

    public static boolean insert(InvoiceFood ifd) {
        int result = 0;
        try {
            String sql = "INSERT INTO invoiceFood (invoiceId, foodId, quantity) VALUES (?, ?, ?)";
            PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prst.setInt(1, ifd.getInvoiceId());
            prst.setInt(2, ifd.getFoodId());
            prst.setInt(3, ifd.getQuantity());
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                ifd.setId(rs.getInt(1));
            }
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static List<InvoiceFood> findAll(String condition) {
        List<InvoiceFood> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceFood " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int invoiceId = rs.getInt("invoiceId");
                int foodId = rs.getInt("foodId");
                int quantity = rs.getInt("quantity");
                InvoiceFood ifd = new InvoiceFood(id, invoiceId, foodId, quantity);
                list.add(ifd);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }
    
    public static int countAllByInvoice(String condition){
        String sql = "SELECT DISTINCT invoice.* FROM invoiceFood " + condition;
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
            Logger.getLogger(InvoiceFoodModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
