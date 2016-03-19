/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import model.FoodModel;

/**
 *
 * @author Hoangha.FPT
 */
public class Menu {

    private int id;
    private String name;
    private int order;

    public Menu(int id, String name, int order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public Menu(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Food> getFoods() {
        return FoodModel.findAll("menuId = " + this.id);
    }
}
