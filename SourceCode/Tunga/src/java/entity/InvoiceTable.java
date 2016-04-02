/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import model.TableModel;

/**
 *
 * @author Hoangha.FPT
 */
public class InvoiceTable {

    private int id;
    private int invoiceId;
    private int tableId;
    private float price;
    private String fromTime;
    private String toTime;

    public InvoiceTable() {
    }

    public InvoiceTable(int id, int invoiceId, int tableId, float price, String fromTime, String toTime) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.tableId = tableId;
        this.price = price;
        this.fromTime = fromTime;
        this.toTime = toTime;
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

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public Table getTable() {
        return TableModel.find(this.tableId);
    }
}
