package com.Altir.HackerEarth.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.Altir.HackerEarth.dao.RoomRepository;
import com.Altir.HackerEarth.model.Booking;
import com.Altir.HackerEarth.model.Dates;
import com.Altir.HackerEarth.model.Person;
import com.Altir.HackerEarth.model.Room;
import com.Altir.HackerEarth.model.Users;
import com.Altir.HackerEarth.services.RoomService;
import com.Altir.HackerEarth.services.UserService;
import com.Altir.HackerEarth.utils.Request;
import com.Altir.HackerEarth.utils.ResponseUtil;

import org.hibernate.mapping.PrimitiveArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    //User is allowed to fetch only those rooms which are not booked
    @GetMapping("/viewBookedRooms")
    public ResponseEntity<Object> viewBookedRooms()
    {
       try {
           return roomService.findRoomsByStatus(1);
       } catch (Exception e) {
           //TODO: handle exception
           return ResponseUtil.errorResponse(null,"Exception thrown", HttpStatus.valueOf(500));
       }
    }
    @GetMapping("/viewAvailableRooms")
    public ResponseEntity<Object> viewAvailableRooms()
    {
       try {
        return roomService.findRoomsByStatus(0);
       } catch (Exception e) {
           //TODO: handle exception
           return ResponseUtil.errorResponse(null,"Exception thrown", HttpStatus.valueOf(500));
       }
    }
    @PostMapping("/addUser") // add single room for 1 or more days
    public ResponseEntity<Object> addUser(@RequestBody Request req)
    {
        try {
            String email=req.getEmail();
            String password=req.getPassword();
            Integer role=req.getRole();
            Users user =new Users(email,password,role);
            String firstName=req.getFirstName();
            String LastName=req.getLastName();
            String address=req.getAddress();
            Integer phoneNo=req.getPhoneNo();
            Person person=new Person(firstName,LastName,phoneNo,address,user);
           return userService.addUser(user,person);
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    @PostMapping("/addSingleRoomBooking") // add single room for 1 or more days
    public ResponseEntity<Object> bookSingleRoom(@RequestBody Request req)
    {
      
        return roomService.addSingleRoomBooking(req);
    }
    @PostMapping("/addMultipleRoomBooking") //book multiple rooms for a single date
    public ResponseEntity<Object> addMultipleRoomBooking(@RequestBody Request req)
    {
        return roomService.addMultipleRoomBooking(req);
    }
}
