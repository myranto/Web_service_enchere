package com.my.ws_encheres.service.push;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.FireRepository;
import com.my.ws_encheres.model.notif.Fire_token;
import com.my.ws_encheres.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Fire_TokenService implements FireService {
    private final FireRepository rep ;

    public Fire_TokenService(FireRepository rep) {
        this.rep = rep;
    }

    @Override
    public void save(Fire_token tok) {
        rep.save(tok);
    }
    @Override
    public Fire_token findTokenByIdClient(int idclient) {
        return rep.findTopByIdclientOrderByIdDesc(idclient);
    }
}
