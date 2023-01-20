package com.my.ws_encheres.service;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.AdminRepository;
import com.my.ws_encheres.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    public ResponseEntity<ToJsonData> login(Admin cli);
    public ResponseEntity<ToJsonData> creates(Admin toCreate);

}
