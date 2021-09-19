package com.Altir.HackerEarth.controllers;

import com.Altir.HackerEarth.model.Room;
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
        return adminService.fetchAvailableRooms();
    }
    @GetMapping("/fetchBookedRooms")
    public ResponseEntity<Object> fetchBookedRooms()
    {
        return adminService.fetchBookedRooms();
    }
    @PostMapping("/addRooms") // add single room for 1 or more days
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
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    @PostMapping("/removeRooms") // add single room for 1 or more days
    public ResponseEntity<Object> removeRooms(@RequestBody Request req)
    {
        try {
            Integer status=req.getStatus();
            Integer roomNo=req.getRoomNo();
            String type=req.getTypeOfRoom();
            Integer price=req.getPrice();
            Room room=new Room(roomNo,status,type,price);
            return roomService.removeRooms(room);

        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    @PostMapping("/updateRooms") // add single room for 1 or more days
    public ResponseEntity<Object> updateRooms(@RequestBody Request req)
    {
        try {
            Integer status=req.getStatus();
            Integer roomNo=req.getRoomNo();
            String type=req.getTypeOfRoom();
            Integer price=req.getPrice();
            Room room=new Room(roomNo,status,type,price);
            return roomService.updateRooms(room);

        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
}
