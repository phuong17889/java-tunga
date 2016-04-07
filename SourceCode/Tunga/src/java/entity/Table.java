/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.SQLException;
import model.RoomModel;
import utility.Helper;

/**
 *
 * @author Hoangha.FPT
 */
public class Table {

    private int id;
    private int roomId;
    private String name;
    private String description;
    private String image;
    private int type;
    private float price;

    public Table(int id, int roomId, String name, String description, String image, int type, float price) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.type = type;
        this.price = price;
    }

    public Table() {
    }

    public Table(int roomId, String name, String description, String image, int type, float price) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return Helper.baseUrl() + "/uploads/tables/" + image;
    }

    public Room getRoom() throws SQLException {
        return RoomModel.find(this.roomId);
    }
}
