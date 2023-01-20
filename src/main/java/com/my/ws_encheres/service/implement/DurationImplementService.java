package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.DurationRepository;
import com.my.ws_encheres.model.enchere.Duration;
import com.my.ws_encheres.service.DurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DurationImplementService implements DurationService {
    private final DurationRepository rep;

    public DurationImplementService(DurationRepository rep) {
        this.rep = rep;
    }

    @Override
    public ResponseEntity<ToJsonData> create(Duration dure) {
        try {
            rep.save(dure);
            return new ResponseEntity<>(new ToJsonData("success",null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> findDesc() {
        try {

            return new ResponseEntity<>(new ToJsonData(rep.findDurationByIdDesc(),null),HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
