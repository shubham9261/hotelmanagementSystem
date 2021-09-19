package com.Altir.HackerEarth.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.Altir.HackerEarth.model.Users;
public interface UserRepository extends JpaRepository<Users,Integer> {
    public Optional<Users> findByEmail(String email);
}
