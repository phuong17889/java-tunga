/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.Model;
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
public class FoodModel extends Model {

    public boolean insert(Food f) {
        int result = 0;
        try {

            String sql = "INSERT INTO food (menu_id, name, price, image) VALUES (?, ?, ?, ?)";
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setInt(1, f.getMenuId());
            prst.setString(2, f.getName());
            prst.setFloat(3, f.getPrice());
            prst.setString(4, f.getImage());
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public boolean update(int id, Food f) {
        int result = 0;
        String sql = "UPDATE food SET menu_id = ?, name = ?, price = ?, image= ? WHERE id = ?";
        try {
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setInt(1, f.getMenuId());
            prst.setString(2, f.getName());
            prst.setFloat(3, f.getPrice());
            prst.setString(4, f.getImage());
            prst.setInt(5, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public List<Food> findAll() {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food";
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int menu_id = rs.getInt("menu_id");
                String name = rs.getString("name");
                float price = rs.getInt("price");
                String image = rs.getString("image");
                Food f = new Food(id, menu_id, name, price, image);
                list.add(f);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Food> findAll(String condition) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE " + condition;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int menu_id = rs.getInt("menu_id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String image = rs.getString("image");
                Food f = new Food(id, menu_id, name, price, image);
                list.add(f);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Food find(int id) {
        Food f = null;
        String sql = "SELECT * FROM food WHERE id = " + id;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                f = new Food(rs.getInt("id"), rs.getInt("menu_id"), rs.getString("name"), rs.getFloat("price"), rs.getString("image"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public Food find(String condition) {
        Food f = null;
        String sql = "SELECT * FROM food WHERE " + condition;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                f = new Food(rs.getInt("id"), rs.getInt("menu_id"), rs.getString("name"), rs.getFloat("price"), rs.getString("image"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public boolean delete(int id) {
        int result = 0;
        String sql = "DELETE FROM food WHERE id = ?";
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
        String sql = "DELETE FROM food WHERE " + condition;
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
