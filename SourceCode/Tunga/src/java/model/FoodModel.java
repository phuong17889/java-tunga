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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TuanDo
 */
public class FoodModel extends EntityModel {

    public static boolean insert(Food f) {
        int result = 0;
        try {
            String sql = "INSERT INTO food (menuId, name, description, price, image) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prst = em.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static boolean update(int id, Food f) {
        int result = 0;
        String sql = "UPDATE food SET menuId = ?, name = ?, description = ?, price = ?, image= ? WHERE id = ?";
        try {
            PreparedStatement prst = em.getConnection().prepareStatement(sql);
            prst.setInt(1, f.getMenuId());
            prst.setString(2, f.getName());
            prst.setString(3, f.getDescription());
            prst.setFloat(4, f.getPrice());
            prst.setString(5, f.getImage());
            prst.setInt(6, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static List<Food> findAll() {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food";
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
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
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }

    public static List<Food> findAll(String condition) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
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
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.size() > 0 ? list : null;
    }

    public static Food find(int id) {
        Food f = null;
        String sql = "SELECT * FROM food WHERE id = " + id;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                f = new Food(rs.getInt("id"), rs.getInt("menuId"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("image"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Food find(String condition) {
        Food f = null;
        String sql = "SELECT * FROM food WHERE " + condition;
        try {
            Statement st = em.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                f = new Food(rs.getInt("id"), rs.getInt("menuId"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("image"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static boolean delete(int id) {
        int result = 0;
        String sql = "DELETE FROM food WHERE id = ?";
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
        String sql = "DELETE FROM food WHERE " + condition;
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
