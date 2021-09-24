package com.Altir.HackerEarth.services;

import java.util.ArrayList;
import java.util.List;

import com.Altir.HackerEarth.dao.BookingRepository;
import com.Altir.HackerEarth.dao.RoomRepository;
import com.Altir.HackerEarth.model.Booking;
import com.Altir.HackerEarth.model.Room;
import com.Altir.HackerEarth.model.Users;
import com.Altir.HackerEarth.utils.Request;
import com.Altir.HackerEarth.utils.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    public ResponseEntity<Object> getAllRooms() {
        try {
            List<Room> room=roomRepository.findAll();
            return ResponseUtil.successResponse(room,"Details of all the rooms are sent", HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }

    public ResponseEntity<Object> fetchRevenue() {
     try {
        List<Booking> bookings=new ArrayList<>();
        bookingRepository.findAll().
        forEach(bookings::add);
        Integer totalPrice=0;
        for(Integer i=0;i<bookings.size();i++)
        {
            totalPrice+=bookings.get(i).getPrice();
        }
        return ResponseUtil.successResponse(totalPrice,"Details of all the rooms are sent", HttpStatus.valueOf(200));
     } catch (Exception e) {
         return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
     }
        
    }
    
    public ResponseEntity<Object> addRooms(@RequestBody Request req) {
        try {
            Users user=new Users(req.getFirstName(),req.getLastName(),
                req.getPhoneNo().req.getAddress(),req.getEmail(),req.get);
            roomRepository.add(room);
            return ResponseUtil.successResponse(room,"Room is added successfullly", HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }

    public ResponseEntity<Object> removeRooms(Room room) {
        try {
            roomRepository.delete(room);
            return ResponseUtil.successResponse(room,"Room is deleted successfullly", HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }

    public ResponseEntity<Object> updateRooms(Room room) {
        try {
            roomRepository.save(room);
            return ResponseUtil.successResponse(room,"Room is updated successfullly", HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    
}
