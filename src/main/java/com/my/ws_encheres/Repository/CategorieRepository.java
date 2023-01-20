package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
    public ArrayList<Categorie> findAllById(int id);

}
