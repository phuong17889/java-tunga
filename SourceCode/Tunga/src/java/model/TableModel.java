/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.Model;
import entity.Table;
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
 * @author notte
 */
public class TableModel extends Model {

    public boolean insert(Table t) {
        int result = 0;
        try {
            String sql = "INSERT INTO [tunga].[dbo].[table] (room_id, name, type, price) VALUES (?, ?, ?, ?)";
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setInt(1, t.getRoomId());
            prst.setString(2, t.getName());
            prst.setInt(3, t.getType());
            prst.setFloat(4, t.getPrice());
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public boolean update(int id, Table t) {
        int result = 0;
        String sql = "UPDATE [tunga].[dbo].[table] set room_id = ?, name = ?, type = ?, price = ? where id = ?";
        try {
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setInt(1, t.getRoomId());
            prst.setString(2, t.getName());
            prst.setInt(3, t.getType());
            prst.setFloat(4, t.getPrice());
            prst.setInt(5, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public List<Table> findAll() {
        List<Table> list = new ArrayList<Table>();
        String sql = "SELECT * FROM [tunga].[dbo].[table]";
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                float price = rs.getFloat("price");
                Table t = new Table(id, room_id, name, type, price);
                list.add(t);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Table> findAll(String condition) {
        List<Table> list = new ArrayList<Table>();
        String sql = "SELECT * FROM [tunga].[dbo].[table] WHERE " + condition;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                float price = rs.getFloat("price");
                Table t = new Table(id, room_id, name, type, price);
                list.add(t);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Table find(int id) {
        Table t = null;
        String sql = "SELECT * FROM [tunga].[dbo].[table] WHERE id = " + id;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                t = new Table(rs.getInt("id"), rs.getInt("room_id"), rs.getString("name"), rs.getInt("type"), rs.getFloat("price"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public Table find(String condition) {
        Table t = null;
        String sql = "SELECT * FROM [tunga].[dbo].[table] WHERE " + condition;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                t = new Table(rs.getInt("id"), rs.getInt("room_id"), rs.getString("name"), rs.getInt("type"), rs.getFloat("price"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public boolean delete(int id) {
        int result = 0;
        String sql = "DELETE FROM [tunga].[dbo].[table] WHERE id = ?";
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
        String sql = "DELETE FROM [tunga].[dbo].[table] WHERE " + condition;
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
