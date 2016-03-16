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
public class Invoice_food {
    private int Id;
    private int Invoice_id;
    private int Food_id;
    private int Quantity;

    public Invoice_food(int Id, int Invoice_id, int Food_id, int Quantity) {
        this.Id = Id;
        this.Invoice_id = Invoice_id;
        this.Food_id = Food_id;
        this.Quantity = Quantity;
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

    public int getFood_id() {
        return Food_id;
    }

    public void setFood_id(int Food_id) {
        this.Food_id = Food_id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
    
}
