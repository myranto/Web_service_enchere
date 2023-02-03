package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.notif.Fire_token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireRepository extends JpaRepository<Fire_token,Integer> {
    public Fire_token findTopByIdclientOrderByIdDesc(int idclient);
}
