package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AdminRepository extends MongoRepository<Admin,Integer> {
    Admin findAdminByEmailAndAndMdp(String email,String mdp);
}
