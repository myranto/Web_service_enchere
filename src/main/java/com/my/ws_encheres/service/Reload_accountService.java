package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Reload_account;
import org.springframework.http.ResponseEntity;

public interface Reload_accountService {

    public ResponseEntity<ToJsonData> save(Reload_account ra);
    public ResponseEntity<ToJsonData> selectAll();
    public ResponseEntity<ToJsonData> deleteById(int id);

    public ResponseEntity<ToJsonData> delete(int id);

    public ResponseEntity<ToJsonData> update(Reload_account ra, int id);

    public ResponseEntity<ToJsonData> findAllById(int id);

    public ResponseEntity<ToJsonData> getOneById(int id);

}
