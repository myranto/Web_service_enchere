package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.Reload_account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Repository
public interface Reload_accountRepository extends JpaRepository<Reload_account,Integer> {

    public Reload_account findReload_accountById(int id);
    @Query("select c from Reload_account  c where c.idclient.id=:idc order by c.id desc LIMIT 1")
    public Reload_account findReload_accountByIdclientOrderByIdDesc(@Param("idc") int idclient);

    @Query("select c from Reload_account c where est_valider=0")
    public ArrayList<Reload_account> findReload_accountByEst_valider();

}
