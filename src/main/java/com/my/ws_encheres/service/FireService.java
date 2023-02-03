package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.notif.Fire_token;
import org.springframework.http.ResponseEntity;

public interface FireService {
    public void save(Fire_token tok);
    public Fire_token findTokenByIdClient(int idclient);
}
