package com.Altir.HackerEarth.controllers;

import java.util.Optional;

import com.Altir.HackerEarth.model.Room;
import com.Altir.HackerEarth.dao.*;
import com.Altir.HackerEarth.services.AdminService;
import com.Altir.HackerEarth.utils.Request;
import com.Altir.HackerEarth.utils.ResponseUtil;

import com.Altir.HackerEarth.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @GetMapping("/viewRooms")
    public ResponseEntity<Object> getAllRooms()
    {
        return adminService.getAllRooms();
    }
    @GetMapping("/fetchRevenue")
    public ResponseEntity<Object> fetchRevenue()
    {
        return adminService.fetchRevenue();
    }
    @GetMapping("/fetchAvailableRooms")
    public ResponseEntity<Object> fetchAvailableRooms()
    {
        return roomService.findRoomsByStatus(0);
    }
    @GetMapping("/fetchBookedRooms")
    public ResponseEntity<Object> fetchBookedRooms()
    {
        return roomService.findRoomsByStatus(1);
    }
    @PostMapping("/addRooms") 
    public ResponseEntity<Object> addRooms(@RequestBody Request req)
    {
        try {
            Integer status=req.getStatus();
            Integer roomNo=req.getRoomNo();
            String type=req.getTypeOfRoom();
            Integer price=req.getPrice();
            Room room=new Room(roomNo,status,type,price);
            return roomService.addRooms(room);

        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    @PostMapping("/removeRooms") 
    public ResponseEntity<Object> removeRooms(@RequestBody Request req)
    {
        try {
            Integer roomNo=req.getRoomNo();
            return roomService.removeRooms(roomNo);

        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    @PostMapping("/updateRooms")
    public ResponseEntity<Object> updateRooms(@RequestBody Request req)
    {
        try {
            Optional<Room> rentedRoom=roomRepository.findById(req.getRoomId());
            if(rentedRoom.isEmpty())
            {
                return ResponseUtil.errorResponse(null,"Room does not exists in the database",HttpStatus.valueOf(500));
            }
            if(req.getStatus()!=null)
                rentedRoom.get().setStatus(req.getStatus());
            if(req.getTypeOfRoom()!=null)
                rentedRoom.get().setTypeOfRoom(req.getTypeOfRoom());
            if(req.getRoomNo()!=null)
                rentedRoom.get().setRoomNo(req.getRoomNo());
            if(req.getPrice()!=null)
                rentedRoom.get().setPrice(req.getPrice());
            return roomService.updateRooms(rentedRoom.get());
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, e.getMessage(),HttpStatus.valueOf(500));
        }
    }
}
