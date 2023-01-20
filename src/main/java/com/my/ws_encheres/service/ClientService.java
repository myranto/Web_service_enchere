package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Client;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    public ResponseEntity<ToJsonData> login(Client cli);
    public ResponseEntity<ToJsonData> Logout(int id)throws Exception;
    public ResponseEntity<ToJsonData> creates(Client cli);
}
