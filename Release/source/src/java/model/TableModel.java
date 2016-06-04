/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import entity.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author notte
 */
public class TableModel extends EntityModel {

    public static boolean insert(Table t) throws SQLException {
        int result;
        String sql = "INSERT INTO [tunga].[dbo].[table] (roomId, name, type, price, description, image) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prst.setInt(1, t.getRoomId());
            prst.setString(2, t.getName());
            prst.setInt(3, t.getType());
            prst.setFloat(4, t.getPrice());
            prst.setString(5, t.getDescription());
            prst.setString(6, t.getImage());
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                t.setId(rs.getInt(1));
            }
        }
        return result > 0;
    }

    public static boolean update(int id, Table t) throws SQLException {
        int result;
        String sql = "UPDATE [tunga].[dbo].[table] SET roomId = ?, name = ?, type = ?, price = ?, description = ?, image = ? WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setInt(1, t.getRoomId());
            prst.setString(2, t.getName());
            prst.setInt(3, t.getType());
            prst.setFloat(4, t.getPrice());
            prst.setString(5, t.getDescription());
            prst.setString(6, t.getImage());
            prst.setInt(7, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static List<Table> findAll() throws SQLException {
        List<Table> list = new ArrayList<>();
        String sql = "SELECT * FROM [tunga].[dbo].[table]";
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int roomId = rs.getInt("roomId");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Table t = new Table(id, roomId, name, description, image, type, price);
                list.add(t);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Table> findAll(String condition) throws SQLException {
        List<Table> list = new ArrayList<>();
        String sql = "SELECT * FROM [tunga].[dbo].[table] " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int roomId = rs.getInt("roomId");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Table t = new Table(id, roomId, name, description, image, type, price);
                list.add(t);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static Table find(int id) throws SQLException {
        Table t = null;
        String sql = "SELECT * FROM [tunga].[dbo].[table] WHERE id = " + id;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                t = new Table(rs.getInt("id"), rs.getInt("roomId"), rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("type"), rs.getFloat("price"));
            }
        }
        return t;
    }

    public static Table find(String condition) throws SQLException {
        Table t = null;
        String sql = "SELECT * FROM [tunga].[dbo].[table] " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                t = new Table(rs.getInt("id"), rs.getInt("roomId"), rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("type"), rs.getFloat("price"));
            }
        }
        return t;
    }

    public static boolean delete(int id) throws SQLException {
        int result;
        String sql = "DELETE FROM [tunga].[dbo].[table] WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setInt(1, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static boolean delete(String condition) throws SQLException {
        int result;
        String sql = "DELETE FROM [tunga].[dbo].[table] " + condition;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            result = prst.executeUpdate();
        }
        return result > 0;
    }
}
