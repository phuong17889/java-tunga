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
public class Room {
    private int Id;
    private String Name;
    private boolean Type;

    public Room(int Id, String Name, boolean Type) {
        this.Id = Id;
        this.Name = Name;
        this.Type = Type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public boolean isType() {
        return Type;
    }

    public void setType(boolean Type) {
        this.Type = Type;
    }
    
    
}
