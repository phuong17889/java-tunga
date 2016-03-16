/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hoangha.FPT
 */
public class Invoice_table {
    private int Id;
    private int Invoice_id;
    private int Table_id;
    private float Price;
    private String From_time;
    private String To_time;

    public Invoice_table(int Id, int Invoice_id, int Table_id, float Price, String From_time, String To_time) {
        this.Id = Id;
        this.Invoice_id = Invoice_id;
        this.Table_id = Table_id;
        this.Price = Price;
        this.From_time = From_time;
        this.To_time = To_time;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getInvoice_id() {
        return Invoice_id;
    }

    public void setInvoice_id(int Invoice_id) {
        this.Invoice_id = Invoice_id;
    }

    public int getTable_id() {
        return Table_id;
    }

    public void setTable_id(int Table_id) {
        this.Table_id = Table_id;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getFrom_time() {
        return From_time;
    }

    public void setFrom_time(String From_time) {
        this.From_time = From_time;
    }

    public String getTo_time() {
        return To_time;
    }

    public void setTo_time(String To_time) {
        this.To_time = To_time;
    }
    
    
}
