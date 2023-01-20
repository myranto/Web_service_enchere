package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import org.springframework.http.ResponseEntity;

public interface CompteService {
    public ResponseEntity<ToJsonData> getAccount(int idclient);
    public ResponseEntity<ToJsonData> Reload_Account(int idclient);
}
