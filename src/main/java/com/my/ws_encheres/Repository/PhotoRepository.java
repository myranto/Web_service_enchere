package com.my.ws_encheres.Repository;


import com.my.ws_encheres.model.Categorie;
import com.my.ws_encheres.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface PhotoRepository extends MongoRepository<Photo,Integer>
{

    public ArrayList<Photo> findAllByIdEnchere(int id);
}
