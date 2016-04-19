/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.SQLException;
import model.MenuModel;
import utility.Helper;

/**
 *
 * @author Hoangha.FPT
 */
public class Food {

    private int id;
    private int menuId;
    private String name;
    private float price;
    private String image;
    private String description;

    public Food(int id, int menuId, String name, String description, float price, String image) {
        this.id = id;
        this.menuId = menuId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Food(int menuId, String name, String description, float price, String image) {
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        String shortDesc = "";
        if (this.description != null && !"".equals(this.description)) {
            String[] desc = this.description.split(" ");
            for (int i = 0; i < 10; i++) {
                try {
                    shortDesc += desc[i] + " ";
                } catch (Exception e) {
                    return "";
                }
            }
        }
        return shortDesc.trim();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return Helper.baseUrl() + "/uploads/foods/" + image;
    }

    public Menu getMenu() throws SQLException {
        return MenuModel.find(this.menuId);
    }

    public int getPriceInt() {
        return (int) this.price;
    }
}
