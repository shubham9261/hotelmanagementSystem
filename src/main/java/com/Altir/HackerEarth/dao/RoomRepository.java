package com.Altir.HackerEarth.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.Altir.HackerEarth.model.Room;
public interface RoomRepository extends JpaRepository<Room,Integer>  {
    public List<Room> findByStatus(Integer status);
}
