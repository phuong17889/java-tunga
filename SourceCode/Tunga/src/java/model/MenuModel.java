/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.EntityModel;
import entity.Food;
import entity.Menu;
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
public class MenuModel extends EntityModel {

    public static boolean insert(Menu m) throws SQLException {
        String sql = "INSERT INTO menu (name, [order]) VALUES(?, ?)";
        int result;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, m.getName());
            prst.setInt(2, m.getOrder());
            result = prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getInt(1));
            }
        }
        return result > 0;
    }

    public static boolean update(int id, Menu m) throws SQLException {
        int result;
        String sql = "UPDATE menu SET name = ?, [order] = ? WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setString(1, m.getName());
            prst.setInt(2, m.getOrder());
            prst.setInt(3, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static List<Menu> findAll() throws SQLException {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu ORDER BY [order]";
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int order = rs.getInt("order");
                Menu m = new Menu(id, name, order);
                list.add(m);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Menu> findAll(String condition) throws SQLException {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu " + condition + " ORDER BY [order]";
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int order = rs.getInt("order");
                Menu m = new Menu(id, name, order);
                list.add(m);
            }
        }
        return list.size() > 0 ? list : null;
    }

    public static Menu find(int id) throws SQLException {
        Menu m = null;
        String sql = "SELECT * FROM menu WHERE id = " + id;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                m = new Menu(rs.getInt("id"), rs.getString("name"), rs.getInt("order"));
            }
        }
        return m;
    }

    public static Menu find(String condition) throws SQLException {
        Menu m = null;
        String sql = "SELECT * FROM menu " + condition;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                m = new Menu(rs.getInt("id"), rs.getString("name"), rs.getInt("order"));
            }
        }
        return m;
    }

    public static boolean delete(int id) throws SQLException {
        int result;
        String sql = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            prst.setInt(1, id);
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static boolean delete(String condition) throws SQLException {
        int result;
        String sql = "DELETE FROM menu " + condition;
        try (PreparedStatement prst = em.getConnection().prepareStatement(sql)) {
            result = prst.executeUpdate();
        }
        return result > 0;
    }

    public static List<Food> getFoods(int menuId) throws SQLException {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE menuId = " + menuId;
        try (Statement st = em.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String image = rs.getString("image");
                Food f = new Food(id, menuId, description, name, price, image);
                list.add(f);
            }
        }
        return list.size() > 0 ? list : null;
    }
}
