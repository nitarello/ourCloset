package com.xim.ourcloset.repositories;

import com.xim.ourcloset.models.Carta;
import com.xim.ourcloset.models.Modello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartaDAO extends JpaRepository<Carta, Integer> {
    List<Carta> findByUser_Userid(int userId);
    Carta findById(int id);
}
