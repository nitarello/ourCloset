package com.xim.ourcloset.repositories;

import com.xim.ourcloset.models.Collezione;
import com.xim.ourcloset.models.Modello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelloDAO extends JpaRepository<Modello,Integer> {
}
