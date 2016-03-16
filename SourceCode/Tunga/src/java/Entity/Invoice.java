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
public class Invoice {
    private int Id;
    private String Full_name;
    private String Address;
    private String Phone;
    private float Tax;
    private float Total;
    private String Token;
    private boolean Status;
    private String Create_at;

    public Invoice(int Id, String Full_name, String Address, String Phone, float Tax, float Total, String Token, boolean Status, String Create_at) {
        this.Id = Id;
        this.Full_name = Full_name;
        this.Address = Address;
        this.Phone = Phone;
        this.Tax = Tax;
        this.Total = Total;
        this.Token = Token;
        this.Status = Status;
        this.Create_at = Create_at;
    }
    
    
}
