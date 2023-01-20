package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import org.springframework.http.ResponseEntity;

public interface StatistiqueService {
    public ResponseEntity<ToJsonData> getStat();
}
