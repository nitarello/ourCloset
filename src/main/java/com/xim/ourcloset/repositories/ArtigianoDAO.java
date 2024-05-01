package com.xim.ourcloset.repositories;

import com.xim.ourcloset.models.Artigiano;
import com.xim.ourcloset.models.Collezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtigianoDAO extends JpaRepository<Artigiano,Integer> {

}
