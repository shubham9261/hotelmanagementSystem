package com.Altir.HackerEarth.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer roomNo;
    private Integer status;//0 for available 1 for booked
    private String typeOfRoom;
    private Integer price;
    public Integer getId() {
        return id;
    }
    public Room(){}
    public Room(Integer roomNo, Integer status, String typeOfRoom, Integer price) {
        this.roomNo = roomNo;
        this.status = status;
        this.typeOfRoom = typeOfRoom;
        this.price = price;
    }
    
    public Room(Integer id) {
        this.id = id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTypeOfRoom() {
        return typeOfRoom;
    }
    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    
    
    
}
