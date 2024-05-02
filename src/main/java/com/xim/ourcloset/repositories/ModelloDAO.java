package com.xim.ourcloset.repositories;

import com.xim.ourcloset.models.Modello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelloDAO extends JpaRepository<Modello,Integer> {
    List<Modello> findByCollezione_Artigiano_Idart(int idart);
}
