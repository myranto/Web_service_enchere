package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.MyExecption.RessourceNotFoundException;
import com.my.ws_encheres.Repository.CategorieRepository;
import com.my.ws_encheres.model.Categorie;
import com.my.ws_encheres.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategorieImplementService implements CategorieService {
    private final CategorieRepository rep;

    public CategorieImplementService(CategorieRepository rep) {
        this.rep = rep;
    }

    @Override
    public ResponseEntity<ToJsonData> save(Categorie catego) {
        try {
            rep.save(catego);
            return new ResponseEntity<>(new ToJsonData("success",null), HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> selectAll() {
        return new ResponseEntity<>(new ToJsonData(rep.findAll(),null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToJsonData> update(int idc,String name) {
        try {
            Categorie cat = rep.findById(idc).orElseThrow(()->new RessourceNotFoundException("Categorie","id",idc));
            cat.setNom(name);
            rep.save(cat);
            return new ResponseEntity<>(new ToJsonData("success",null), HttpStatus.ACCEPTED);
        }catch (Exception e){

            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> deleteById(int id) {
        try {
            rep.findById(id).orElseThrow(()->new RessourceNotFoundException("Categorie","id",id));
            rep.deleteById(id);
            return new ResponseEntity<>(new ToJsonData("delete success",null), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> findAllById(int id) {
        try {
            return new ResponseEntity<>(new ToJsonData(rep.findAllById(id),null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
