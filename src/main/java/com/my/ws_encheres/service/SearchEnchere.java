package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.fiche.RequestSearch;
import com.my.ws_encheres.model.enchere.Enchere;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SearchEnchere {
    public List<Enchere> searchByKeyWord(String key);
    public ResponseEntity<ToJsonData> searchAdvanced(RequestSearch o);
}
