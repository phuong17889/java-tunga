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
public class Table {
    private int Id;
    private int Room_id;
    private String Name;
    private int Type;
    private float Price;

    public Table(int Id, int Room_id, String Name, int Type, float Price) {
        this.Id = Id;
        this.Room_id = Room_id;
        this.Name = Name;
        this.Type = Type;
        this.Price = Price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getRoom_id() {
        return Room_id;
    }

    public void setRoom_id(int Room_id) {
        this.Room_id = Room_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
    
    
}
