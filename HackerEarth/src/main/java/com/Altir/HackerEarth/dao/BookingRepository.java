package com.Altir.HackerEarth.dao;
import com.Altir.HackerEarth.model.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    
}
