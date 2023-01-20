package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.enchere.Duration;
import org.springframework.http.ResponseEntity;

public interface DurationService {
    public ResponseEntity<ToJsonData> create(Duration dure);
    public ResponseEntity<ToJsonData> findDesc();
}
