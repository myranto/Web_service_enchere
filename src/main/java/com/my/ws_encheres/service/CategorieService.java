package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Categorie;
import org.springframework.http.ResponseEntity;

public interface CategorieService {
    public ResponseEntity<ToJsonData> save(Categorie catego);
    public ResponseEntity<ToJsonData> selectAll();
    public ResponseEntity<ToJsonData> update(int idc,String name);
    public ResponseEntity<ToJsonData> deleteById(int id);
    public ResponseEntity<ToJsonData> findAllById(int id);
}
