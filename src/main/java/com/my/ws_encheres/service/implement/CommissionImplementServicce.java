package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.CommissionRepository;
import com.my.ws_encheres.model.commission.Commission;
import com.my.ws_encheres.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommissionImplementServicce implements CommissionService {
    private final CommissionRepository rep;

    public CommissionImplementServicce(CommissionRepository rep) {
        this.rep = rep;
    }

    @Override
    public ResponseEntity<ToJsonData> findLastCommission(){
        try {
            Commission find = rep.getLastCommission();
            if (find == null) {
                throw new Exception("there is not commission");
            }
            return new ResponseEntity<>(new ToJsonData(find,null),HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> create(Commission toCreate) {
        try {
            rep.save(toCreate);
            return new ResponseEntity<>(new ToJsonData("success",null), HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
