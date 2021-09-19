package com.Altir.HackerEarth.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Users user;
    @ManyToMany
    private List<Room> rooms;
    @ManyToOne
    private Dates date;
    private Integer price;
    public Booking(){}
    public Booking(Users user, List<Room> rooms, Dates date, Integer price) {
        this.user = user;
        this.rooms = rooms;
        this.date = date;
        this.price = price;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public Dates getDate() {
        return date;
    }
    public void setDate(Dates date) {
        this.date = date;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    
}
