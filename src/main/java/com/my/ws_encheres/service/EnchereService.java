package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.fiche.EnchereParam;
import com.my.ws_encheres.model.enchere.Enchere;
import org.springframework.http.ResponseEntity;

public interface EnchereService {
    public ResponseEntity<ToJsonData> createEnchere(Enchere enchere);
    public ResponseEntity<ToJsonData> createEnchereReal(EnchereParam enchere);
    public ResponseEntity<ToJsonData> selectAll();
    public ResponseEntity<ToJsonData> selectClientEnchere(int idclient);
    public ResponseEntity<ToJsonData> selectClientEnchereReal(int idclient);
    public ResponseEntity<ToJsonData> selectClientEnchereFinished(int idclient);
    public ResponseEntity<ToJsonData> findByEnchere(int idenchere);
    public void isTerminate();
}
