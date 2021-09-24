package com.Altir.HackerEarth.services;

import com.Altir.HackerEarth.dao.BookingRepository;
import com.Altir.HackerEarth.dao.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import com.Altir.HackerEarth.model.*;
import com.Altir.HackerEarth.utils.ResponseUtil;

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
            List<Booking> bookings=bookingRepository.findAll();

            Integer totalPrice=0;
            for(int i=0;i<bookings.size();i++)
            {
                totalPrice+=bookings.get(i).getPrice();
            }
            return ResponseUtil.successResponse(totalPrice,"Net revenue of all the booked rooms are sent", HttpStatus.valueOf(200));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseUtil.errorResponse(null, e.getMessage(),HttpStatus.valueOf(500));
        }
    }

    public ResponseEntity<Object> fetchAvailableRooms() {
        try {
            List<Room> rooms=roomRepository.findAll();
            List<Room> availableRooms=new ArrayList<Room>();
            for(int i=0;i<rooms.size();i++)
            {
                if(rooms.get(i).getStatus()==0){
                    availableRooms.add(rooms.get(i));
                }
            }
            return ResponseUtil.successResponse(availableRooms,"Details of all the available rooms are sent", HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }

    public ResponseEntity<Object> fetchBookedRooms() {
        try {
            List<Room> rooms=roomRepository.findAll();
            List<Room> bookedRooms=new ArrayList<Room>();
            for(int i=0;i<rooms.size();i++)
            {
                if(rooms.get(i).getStatus()>0){
                    bookedRooms.add(rooms.get(i));
                }
            }
            return ResponseUtil.successResponse(bookedRooms,"Details of all the booked rooms are sent", HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }

    // public ResponseEntity<Object> addRooms(@RequestBody Request req) {
    //     try {
    //         Users user=new Users(req.getFirstName(),req.getLastName(),
    //             req.getPhoneNo().req.getAddress(),req.getEmail(),req.get)
    //         roomRepository.add(room);
    //         return ResponseUtil.successResponse(room,"Room is added successfullly", HttpStatus.valueOf(200));
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
    //     }
    // }

    // public ResponseEntity<Object> removeRooms() {
    //     try {
    //         roomRepository.delete(room);
    //         return ResponseUtil.successResponse(room,"Room is deleted successfullly", HttpStatus.valueOf(200));
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
    //     }
    // }

    // public ResponseEntity<Object> updateRooms() {
    //     try {
    //         roomRepository.save(room);
    //         return ResponseUtil.successResponse(room,"Room is updated successfullly", HttpStatus.valueOf(200));
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
    //     }
    // }
    
}
