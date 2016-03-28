/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import static core.EntityModel.em;
import entity.InvoiceTable;
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

}
