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
public class Food {
    private int Id;
    private int Menu_id;
    private String Name;
    private float Price;
    private String Image;

    public Food(int Id, int Menu_id, String Name, float Price, String Image) {
        this.Id = Id;
        this.Menu_id = Menu_id;
        this.Name = Name;
        this.Price = Price;
        this.Image = Image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getMenu_id() {
        return Menu_id;
    }

    public void setMenu_id(int Menu_id) {
        this.Menu_id = Menu_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Food() {
    }
    
    
}


