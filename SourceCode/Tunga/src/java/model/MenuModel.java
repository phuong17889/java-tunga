/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.Model;
import entity.Food;
import entity.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.DataProcess;

/**
 *
 * @author TuanDo
 */
public class MenuModel extends Model {

    public boolean insert(Menu m) {
        int result = 0;

        try {
            String sql = "INSERT INTO menu (name) VALUES(?)";
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setString(1, m.getName());
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(MenuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public boolean update(int id, Menu m) {
        int result = 0;
        String sql = "UPDATE menu SET name = ? WHERE id = ?";
        try {
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            prst.setString(1, m.getName());
            prst.setInt(2, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public List<Menu> findAll() {
        List<Menu> list = new ArrayList<Menu>();
        String sql = "SELECT * FROM menu ORDER BY `order`";
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int order = rs.getInt("order");
                Menu m = new Menu(id, name, order);
                list.add(m);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Menu> findAll(String condition) {
        List<Menu> list = new ArrayList<Menu>();
        String sql = "SELECT * FROM menu WHERE " + condition + " ORDER BY `order`";
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int order = rs.getInt("order");
                Menu m = new Menu(id, name, order);
                list.add(m);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Menu find(int id) {
        Menu m = null;
        String sql = "SELECT * FROM menu WHERE id = " + id;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                m = new Menu(rs.getInt("id"), rs.getString("name"),rs.getInt("order"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public Menu find(String condition) {
        Menu m = null;
        String sql = "SELECT * FROM menu WHERE " + condition;
        try {
            Statement st = this.dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                m = new Menu(rs.getInt("id"), rs.getString("name"),rs.getInt("order"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public boolean delete(int id) {
        int result = 0;
        String sql = "DELETE FROM menu WHERE id = ?";
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
        String sql = "DELETE FROM menu WHERE " + condition;
        try {
            PreparedStatement prst = this.dt.getConnection().prepareStatement(sql);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public static List<Food> getFoods(int menu_id) {
        DataProcess dt = new DataProcess();
        List<Food> list = new ArrayList<Food>();
        String sql = "SELECT * FROM food WHERE menu_id = " + menu_id;
        try {
            Statement st = dt.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
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
}
