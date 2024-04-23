package com.xim.ourcloset.repositories;

import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {

}
