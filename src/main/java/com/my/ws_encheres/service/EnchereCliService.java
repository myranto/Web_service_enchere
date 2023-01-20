package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.enchere.EnchereCli;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface EnchereCliService {
    public ResponseEntity<ToJsonData> Rencherir(EnchereCli cli, String token);
}
