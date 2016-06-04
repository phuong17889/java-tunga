/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import entity.Food;
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
public class FoodModel extends EntityModel {

    public static boolean insert(Food f) throws SQLException {
        String sql = "INSERT INTO food (menuId, name, description, price, image) VALUES (?, ?, ?, ?, ?)";
        int result;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prst.setInt(1, f.getMenuId());
            prst.setString(2, f.getName());
            prst.setString(3, f.getDescription());
            prst.setFloat(4, f.getPrice());
            prst.setString(5, f.getImage());
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                f.setId(rs.getInt(1));
            }
        }
        return result > 0;
    }

    public static boolean update(int id, Food f) throws SQLException {
        String sql = "UPDATE food SET menuId = ?, name = ?, description = ?, price = ?, image= ? WHERE id = ?";
        int result;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setInt(1, f.getMenuId());
            prst.setString(2, f.getName());
            prst.setString(3, f.getDescription());
            prst.setFloat(4, f.getPrice());
            prst.setString(5, f.getImage());
            prst.setInt(6, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static List<Food> findAll() throws SQLException {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food";
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int menuId = rs.getInt("menuId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getInt("price");
                String image = rs.getString("image");
                Food f = new Food(id, menuId, name, description, price, image);
                list.add(f);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Food> findAll(String condition) throws SQLException {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int menuId = rs.getInt("menuId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String image = rs.getString("image");
                Food f = new Food(id, menuId, name, description, price, image);
                list.add(f);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static Food find(int id) throws SQLException {
        Food f = null;
        String sql = "SELECT * FROM food WHERE id = " + id;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                f = new Food(rs.getInt("id"), rs.getInt("menuId"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("image"));
            }
        }
        return f;
    }

    public static Food find(String condition) throws SQLException {
        Food f = null;
        String sql = "SELECT * FROM food " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                f = new Food(rs.getInt("id"), rs.getInt("menuId"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("image"));
            }
        }
        return f;
    }

    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM food WHERE id = ?";
        int result;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setInt(1, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static boolean delete(String condition) throws SQLException {
        String sql = "DELETE FROM food " + condition;
        int result;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            result = prst.executeUpdate();
        }
        return result > 0;
    }
}
