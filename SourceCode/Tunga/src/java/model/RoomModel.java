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

/**
 *
 * @author TuanDo
 */
public class RoomModel extends EntityModel {

    public static boolean insert(Room r) throws SQLException {
        int result;
        String sql = "INSERT INTO room (name, type) VALUES (?, ?)";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, r.getName());
            prst.setInt(2, r.getType() ? 1 : 0);
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                r.setId(rs.getInt(1));
            }
        }
        return result > 0;
    }

    public static boolean update(int id, Room r) throws SQLException {
        int result;
        String sql = "UPDATE room SET name = ?, type = ? WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setString(1, r.getName());
            prst.setInt(2, r.getType() ? 1 : 0);
            prst.setInt(3, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static List<Room> findAll() throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                Room r = new Room(id, name, (type == 1));
                list.add(r);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Room> findAll(String condition) throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                Room r = new Room(id, name, (type == 1));
                list.add(r);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static Room find(int id) throws SQLException {
        Room r = null;
        String sql = "SELECT * FROM room WHERE id = " + id;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                r = new Room(rs.getInt("id"), rs.getString("name"), (rs.getInt("type") == 1));
            }
        }
        return r;
    }

    public static Room find(String condition) throws SQLException {
        Room r = null;
        String sql = "SELECT * FROM room " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                r = new Room(rs.getInt("id"), rs.getString("name"), (rs.getInt("type") == 1));
            }
        }
        return r;
    }

    public static boolean delete(int id) throws SQLException {
        int result;
        String sql = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setInt(1, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static boolean delete(String condition) throws SQLException {
        int result;
        String sql = "DELETE FROM room " + condition;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            result = prst.executeUpdate();
        }
        return result > 0;
    }
}
