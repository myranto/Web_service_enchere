package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Photo;
import org.springframework.http.ResponseEntity;

public interface PhotoService {
    public ResponseEntity<ToJsonData> save(Photo photo);
    public ResponseEntity<ToJsonData> selectAll();
    public ResponseEntity<ToJsonData> findAllById(int id);
}
