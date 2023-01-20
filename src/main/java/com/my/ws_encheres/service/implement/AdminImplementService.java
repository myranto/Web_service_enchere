package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.MyExecption.RessourceNotFoundException;
import com.my.ws_encheres.Repository.AdminRepository;
import com.my.ws_encheres.model.Admin;
import com.my.ws_encheres.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminImplementService implements AdminService {
    private final AdminRepository rep;

    public AdminImplementService(AdminRepository rep) {
        this.rep = rep;
    }

    @Override
    public ResponseEntity<ToJsonData> login(Admin cli) {
        try {
            Admin res = rep.findAdminByEmailAndAndMdp(cli.getEmail(), cli.getMdp());
            if (res == null) {
                throw new Exception("email or password not found for email  "+cli.getEmail());
            }
            ToJsonData<Admin,Object> returning = new ToJsonData<>(res,null);
            return new ResponseEntity<ToJsonData>(returning, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> creates(Admin toCreate) {
        try {
        rep.save(toCreate);
        return new ResponseEntity<>(new ToJsonData("success",null),HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


}
