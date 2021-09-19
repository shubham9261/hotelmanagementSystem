package com.Altir.HackerEarth.dao;

import com.Altir.HackerEarth.model.Dates;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Dates,Integer> {
    
}
