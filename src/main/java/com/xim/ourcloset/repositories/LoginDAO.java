package com.xim.ourcloset.repositories;

import com.xim.ourcloset.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginDAO extends JpaRepository<LoginModel,Integer> {
    Optional<LoginModel> findByUsername(String username);
}
