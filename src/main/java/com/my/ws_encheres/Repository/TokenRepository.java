package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.security.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {
    public void deleteTokenByIdclient(int idclient);
    public Token findTokenByIdclientOrderByDateDesc(int idclient);
    public Token findTopByIdclientOrderByDateDesc(int idclient);
}
