/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author notte
 */
public class Book implements Serializable {

    private String name;
    private String email;
    private String phone;
    private int number;
    private String date;
    private String time;

    public Book(int number, String date, String time) {
        this.number = number;
        this.date = date;
        this.time = time;
    }

    public Book(String name, String email, String phone, int number, String date, String time) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.number = number;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
