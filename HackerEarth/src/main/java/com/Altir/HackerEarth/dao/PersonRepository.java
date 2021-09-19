package com.Altir.HackerEarth.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Altir.HackerEarth.model.Person;
public interface PersonRepository extends JpaRepository<Person,Integer> {
}