/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.FoodModel;

/**
 *
 * @author Hoangha.FPT
 */
public class InvoiceFood {

    private int id;
    private int invoiceId;
    private int foodId;
    private int quantity;
    HashMap<Integer, Integer> cartFood;

    public InvoiceFood() {
        cartFood = new HashMap<>();
    }

    public HashMap<Integer, Integer> getCartFood() {
        return cartFood;
    }

    public void addCartFood(int id, int quantity) {
        if (cartFood.containsKey(id)) {
            int quantityTemp = cartFood.get(id);
            cartFood.remove(id);
            cartFood.put(id, (quantityTemp + quantity));
        } else {
            cartFood.put(id, quantity);
        }
    }

    public void editCartFood(int id, int quantity) {
        if (cartFood.containsKey(id)) {
            cartFood.remove(id);
        }
        cartFood.put(id, quantity);
    }
    
    public void deleteCartFood(int id){
        if (cartFood.containsKey(id)) {
            cartFood.remove(id);
        }
    }

    public float getTotalPrice() throws SQLException {
        float total = 0;
        for (Map.Entry<Integer, Integer> entry : cartFood.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            Food f = FoodModel.find(key);
            total += f.getPrice() * value;
        }
        return total;
    }

    public int getTotalCount() {
        return cartFood.size();
    }

    public InvoiceFood(int id, int invoiceId, int foodId, int quantity) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public InvoiceFood(int invoiceId, int foodId, int quantity) {
        this.invoiceId = invoiceId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Food> getCartContent() throws SQLException {
        List<Food> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cartFood.entrySet()) {
            int key = entry.getKey();
            Food f = FoodModel.find(key);
            list.add(f);
        }
        return list;
    }

    public int getCartQuantity(int id) {
        int quantityCart = 0;
        for (Map.Entry<Integer, Integer> entry : cartFood.entrySet()) {
            if (id == entry.getKey()) {
                return entry.getValue();
            }
        }
        return quantityCart;
    }
    
    public Food getFood() throws SQLException{
        return FoodModel.find(this.foodId);
    }
}
