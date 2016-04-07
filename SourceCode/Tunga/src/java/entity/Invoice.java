/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.SQLException;
import java.util.List;
import model.InvoiceFoodModel;
import model.InvoiceTableModel;

/**
 *
 * @author Hoangha.FPT
 */
public class Invoice {

    private int id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private float total;
    private String token;
    private int status;
    private String createdAt;
    private int notify;

    public final static int STATUS_CANCELED = 0;
    public final static int STATUS_PENDING = 1;
    public final static int STATUS_SHIPPED = 2;
    public final static int STATUS_DELIVERED = 3;
    public final static int NOTIFY_PENDING = 0;
    public final static int NOTIFY_SENT = 1;
    public final static int NOTIFY_READ = 2;

    public Invoice(int id, String fullName, String email, String address, String phone, float total, String token, int status, String createdAt, int notify) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.token = token;
        this.status = status;
        this.createdAt = createdAt;
        this.notify = notify;
    }

    public Invoice(String fullName, String email, String address, String phone, float total, String token, int status, int notify) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.token = token;
        this.status = status;
        this.notify = notify;
    }

    public Invoice() {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }

    public String getStatusText() {
        String text = "Pending";
        switch (this.status) {
            case 0:
                text = "Canceled";
                break;
            case 1:
                text = "Pending";
                break;
            case 2:
                text = "Shipped";
                break;
            case 3:
                text = "Delivered";
                break;
        }
        return text;
    }

    public String getType() throws SQLException {
        return this.getInvoiceTable() == null ? "food" : "table";
    }

    public List<InvoiceFood> getInvoiceFoods() throws SQLException {
        return InvoiceFoodModel.findAll("WHERE invoiceId = " + this.id);
    }

    public InvoiceTable getInvoiceTable() throws SQLException {
        return InvoiceTableModel.find("WHERE invoiceId = " + this.id);
    }
}
