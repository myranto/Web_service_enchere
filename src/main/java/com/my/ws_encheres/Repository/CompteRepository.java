package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompteRepository extends JpaRepository<Compte,Integer> {
    @Query("select c from Compte c where c.idclient=:idclient order by c.id desc LIMIT 1")
    public Compte findCompteByIdclientOrderByIdDescLimit1(@Param("idclient") int idclient);

}
