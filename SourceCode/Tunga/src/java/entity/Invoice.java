/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Hoangha.FPT
 */
public class Invoice {
    private int id;
    private String fullName;
    private String address;
    private String phone;
    private float tax;
    private float total;
    private String token;
    private boolean status;
    private String createdAt;

    public Invoice(int id, String fullName, String address, String phone, float tax, float total, String token, boolean status, String createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.tax = tax;
        this.total = total;
        this.token = token;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(String createdAt) {
        this.createdAt = createdAt;
    }

   
    
}
