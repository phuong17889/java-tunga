/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import entity.Room;
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
public class RoomModel extends EntityModel {

    public static boolean insert(Room r) {
        int result = 0;
        try {
            String sql = "INSERT INTO room (name, type) VALUES (?, ?)";
            PreparedStatement prst = em.getConnection().prepareStatement(sql);
            prst.setString(1, r.getName());
            prst.setInt(2, r.getType() ? 1 : 0);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static boolean update(int id, Room r) {
        int result = 0;
        String sql = "UPDATE room SET name = ?, type = ? WHERE id = ?";
        try {
            PreparedStatement prst = em.getConnection().prepareStatement(sql);
            prst.setString(1, r.getName());
            prst.setInt(2, r.getType() ? 1 : 0);
            prst.setInt(3, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static List<Room> findAll() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                Room r = new Room(id, name, (type == 1));
                list.add(r);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Room> findAll(String condition) {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                Room r = new Room(id, name, (type == 1));
                list.add(r);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }

    public static Room find(int id) {
        Room r = null;
        String sql = "SELECT * FROM room WHERE id = " + id;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                r = new Room(rs.getInt("id"), rs.getString("name"), (rs.getInt("type") == 1));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public static Room find(String condition) {
        Room r = null;
        String sql = "SELECT * FROM room WHERE " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                r = new Room(rs.getInt("id"), rs.getString("name"), (rs.getInt("type") == 1));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public static boolean delete(int id) {
        int result = 0;
        String sql = "DELETE FROM room WHERE id = ?";
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
        String sql = "DELETE FROM room WHERE " + condition;
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
