package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.statistique.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StatistiqueRepository extends JpaRepository<Statistique,Integer> {
    @Query("select c from Statistique c")
    public ArrayList<Statistique> getStatistique();
}
