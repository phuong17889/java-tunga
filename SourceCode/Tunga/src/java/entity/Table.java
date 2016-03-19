/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import model.RoomModel;

/**
 *
 * @author Hoangha.FPT
 */
public class Table {

    private int id;
    private int roomId;
    private String name;
    private int type;
    private float price;

    public Table(int id, int roomId, String name, int type, float price) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Table() {
    }

    public Table(int roomId, String name, int type, float price) {
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Room getRoom() {
        return RoomModel.find(this.roomId);
    }
}
