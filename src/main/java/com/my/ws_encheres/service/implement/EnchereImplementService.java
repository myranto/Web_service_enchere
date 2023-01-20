package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.DurationRepository;
import com.my.ws_encheres.Repository.EnchereRepository;
import com.my.ws_encheres.model.enchere.Enchere;
import com.my.ws_encheres.service.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class EnchereImplementService implements EnchereService {
    private final EnchereRepository rep;
    private final DurationRepository dur;

    public EnchereImplementService(EnchereRepository rep, DurationRepository dur) {
        this.rep = rep;
        this.dur = dur;
    }

    @Override
    public ResponseEntity<ToJsonData> createEnchere(Enchere enchere) {
        try {
            isTerminate();
            if (enchere.getDuree()==0)
                enchere.setDuree(dur.findDurationByIdDesc().getValue());
            rep.save(enchere);
            return new ResponseEntity<>(new ToJsonData("enchere created with success",null), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> selectAll() {
        isTerminate();
        return new ResponseEntity<>(new ToJsonData(rep.findEnchereNotFinish(),null),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToJsonData> selectClientEnchere(int idclient) {
        try {
            isTerminate();
            ArrayList<Enchere> list = rep.findEncheresClient(idclient);
            if (list == null) {
                throw new Exception("client id:"+idclient+" doesn't have enchere");
            }
            return new ResponseEntity<>(new ToJsonData(list,null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<ToJsonData> selectClientEnchereFinished(int idclient) {
        try {
            isTerminate();
            ArrayList<Enchere> list = rep.findEnchereByIdclientAndStatusEquals(idclient);
            if (list == null) {
                throw new Exception("client id:"+idclient+" doesn't have enchere");
            }
            return new ResponseEntity<>(new ToJsonData(list,null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void isTerminate() {
        ArrayList<Enchere> list_nontapitra = rep.findEnchereNotFinish();
        for (Enchere e:list_nontapitra) {
            Timestamp tmp = e.getDate_enchere();
            tmp.setHours(tmp.getHours()+e.getDuree());
            Timestamp cur = new Timestamp(System.currentTimeMillis());
            if (cur.compareTo(tmp)>=0) {
                e.setStatus(1);
                rep.save(e);
            }
        }
    }
}
