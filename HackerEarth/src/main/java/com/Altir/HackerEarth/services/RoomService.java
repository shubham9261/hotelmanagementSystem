package com.Altir.HackerEarth.services;

import java.util.List;

import com.Altir.HackerEarth.dao.RoomRepository;
import com.Altir.HackerEarth.model.Room;
import com.Altir.HackerEarth.utils.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    public ResponseEntity<Object> addRooms(Room room) {
        try {
            roomRepository.save(room);
            return   ResponseUtil.successResponse(room, "Room added Successfully",HttpStatus.valueOf(200));
        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
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
            System.out.println(e);
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    public ResponseEntity<Object> removeRooms(Room room) {
        try {
            roomRepository.delete(room);
            return ResponseUtil.successResponse(room, "Room deleted Successfully",HttpStatus.valueOf(200));
        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    public ResponseEntity<Object> updateRooms(Room room) {
        try {
            roomRepository.save(room);
            return ResponseUtil.successResponse(room, "Room updated Successfully",HttpStatus.valueOf(200));
        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    
}
