package com.xim.ourcloset.repositories;

import com.xim.ourcloset.models.Collezione;
import com.xim.ourcloset.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollezioneDAO extends JpaRepository<Collezione,Integer> {
    List<Collezione> findByArtigiano_Idart(int idart);
}
