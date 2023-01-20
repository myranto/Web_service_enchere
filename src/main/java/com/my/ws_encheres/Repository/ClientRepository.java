package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    public Client findClientByEmailAndMdp(String email,String mdp);
}
