package com.Altir.HackerEarth.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Altir.HackerEarth.dao.BookingRepository;
import com.Altir.HackerEarth.dao.DateRepository;
import com.Altir.HackerEarth.dao.PersonRepository;
import com.Altir.HackerEarth.dao.RoomRepository;
import com.Altir.HackerEarth.dao.UserRepository;
import com.Altir.HackerEarth.model.Booking;
import com.Altir.HackerEarth.model.Person;
import com.Altir.HackerEarth.model.Room;
import com.Altir.HackerEarth.model.Users;
import com.Altir.HackerEarth.utils.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DateRepository dateRepository;
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
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    // public ResponseEntity<Object> addSingleRoomBooking(@RequestBody Request request) {
    //     return null;
    // }
    public ResponseEntity<Object> addUser(Users user, Person person) {
        try {
            Optional<Users> CurUser=userRepository.findByEmail(user.getEmail());
            if(CurUser.isPresent())
            {
                return ResponseUtil.errorResponse(null, "User is present",HttpStatus.valueOf(500));
            }
            userRepository.save(user);
            personRepository.save(person);
            return ResponseUtil.successResponse(user,"User added Successfully", HttpStatus.valueOf(200));

        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown",HttpStatus.valueOf(500));
        }
    }
    public ResponseEntity<Object> addSingleRoomBooking(Booking booking) {
        try {
            dateRepository.save(booking.getDate());
            bookingRepository.save(booking);
            return ResponseUtil.successResponse(booking,"Room booked Successfully", HttpStatus.valueOf(200));
        } catch (Exception e) {
            //TODO: handle exception
            return ResponseUtil.errorResponse(null, "Exception thrown while booking a room",HttpStatus.valueOf(500));
        }
    }

    // public ResponseEntity<Object> addMultipleRoomBooking() {
    //     return null;
    // }
}
