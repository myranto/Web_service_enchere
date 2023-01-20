package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.commission.Commission;
import org.springframework.http.ResponseEntity;

public interface CommissionService {
    public ResponseEntity<ToJsonData> findLastCommission();
    public  ResponseEntity<ToJsonData> create(Commission toCreate);
}
