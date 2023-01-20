package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.enchere.EnchereCli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnchereCliRepo extends JpaRepository<EnchereCli,Integer> {
    public EnchereCli findTopByIdenchereOrderByMontantDesc(int idenchere);
}
