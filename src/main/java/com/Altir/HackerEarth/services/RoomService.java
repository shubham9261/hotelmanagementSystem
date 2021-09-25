package com.Altir.HackerEarth.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.Altir.HackerEarth.dao.*;
import com.Altir.HackerEarth.model.*;
import com.Altir.HackerEarth.services.*;
import com.Altir.HackerEarth.utils.*;
import org.hibernate.mapping.PrimitiveArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserService userService;
    public ResponseEntity<Object> addRooms(Room room) {
        try {
            roomRepository.save(room);
            return   ResponseUtil.successResponse(room, "Room added Successfully",HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    public ResponseEntity<Object> findRoomsByStatus(Integer Status) {
        try {
             List<Room> rooms=roomRepository.findByStatus(Status);
             if(rooms.size()>0)
             {
                String temp="Booked";
                if(Status==0)
                    temp="Available";
                
                return ResponseUtil.successResponse(rooms, temp+" Rooms",HttpStatus.valueOf(200));
             }
             return ResponseUtil.successResponse(null, "No Rooms Present",HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    public ResponseEntity<Object> removeRooms(Room room) {
        try {
            roomRepository.delete(room);
            return ResponseUtil.successResponse(room, "Room deleted Successfully",HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    public ResponseEntity<Object> updateRooms(Room room) {
        try {
            roomRepository.save(room);
            return ResponseUtil.successResponse(room, "Room updated Successfully",HttpStatus.valueOf(200));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(null, "Exception thrown "+ e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    public ResponseEntity<Object> addSingleRoomBooking(Request req){
            try {
                Room room=new Room(req.getRoomId());
                List<Room> rooms=new ArrayList<>();
                rooms.add(room);
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(req.getStartDate());
                Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(req.getEndDate());
                long timeDiff=date2.getTime()-date1.getTime();
                int temp;
                if(timeDiff<0)
                {
                    return ResponseUtil.errorResponse(null, "Please enter correct date",HttpStatus.valueOf(500));
                }
                else{
                    temp=(int)(timeDiff/(1000*60*60*24))%365;
                }
                Integer days=new Integer(temp);
                Users user=new Users(req.getUserId());
                Dates date=new Dates(date1,date2);
                Optional<Room> rentedRoom=roomRepository.findById(req.getRoomId());
                if(( rentedRoom.isPresent() && (rentedRoom.get().getStatus()==1) ))
                    return ResponseUtil.errorResponse(null, "Room is not available",HttpStatus.valueOf(500));
                rentedRoom.get().setStatus(1);
                roomRepository.save(rentedRoom.get());
                Booking booking=new Booking(user, rooms, date, days*rentedRoom.get().getPrice());
                return userService.addSingleRoomBooking(booking);
            } catch (Exception e) {
                //TODO: handle exception
                return ResponseUtil.errorResponse(null, "Exception thrown "+e.getMessage(),HttpStatus.valueOf(500));
            }
    }
    public ResponseEntity<Object>  addMultipleRoomBooking(Request req){
        try {
            List<Integer> roomsList=req.getRoomList();
            // Room room=new Room(req.getRoomList());
            // List<Room> rooms=new ArrayList<>();
            // rooms.add(room);
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(req.getStartDate());
            Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(req.getEndDate());
            long timeDiff=date2.getTime()-date1.getTime();
            int temp;
            if(timeDiff<0)
            {
                return ResponseUtil.errorResponse(null, "Please enter correct date",HttpStatus.valueOf(500));
            }
            else{
                temp=(int)(timeDiff/(1000*60*60*24))%365;
            }
            Integer days=new Integer(temp);
            Users user=new Users(req.getUserId());
            Dates date=new Dates(date1,date2);
            List<Room> bookedRoom=new ArrayList<>();
            List<Integer> roomsNotBooked=new ArrayList<>();
            Integer totalPrice=0;
            for(Integer i=0;i<roomsList.size();i++)
            {
                Optional<Room> rentedRoom=roomRepository.findById(roomsList.get(i));
                if(( rentedRoom.isPresent() && (rentedRoom.get().getStatus()==1) ))
                {
                    roomsNotBooked.add(rentedRoom.get().getId());
                    continue;
                   // return ResponseUtil.errorResponse(null, "Room is not available",HttpStatus.valueOf(500));
                }
                rentedRoom.get().setStatus(1);
                roomRepository.save(rentedRoom.get());
                bookedRoom.add(rentedRoom.get());
                totalPrice+=(days*rentedRoom.get().getPrice());
            }
            Booking booking=new Booking(user, bookedRoom, date,totalPrice );
            return userService.addSingleRoomBooking(booking);
        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown "+e.getMessage(),HttpStatus.valueOf(500));
        }

    }
    
}
