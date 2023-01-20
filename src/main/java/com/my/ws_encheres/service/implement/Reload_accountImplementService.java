package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.MyExecption.RessourceNotFoundException;
import com.my.ws_encheres.Repository.Reload_accountRepository;
import com.my.ws_encheres.model.Reload_account;
import com.my.ws_encheres.service.Reload_accountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Reload_accountImplementService implements Reload_accountService {


    private final Reload_accountRepository rap;

    public Reload_accountImplementService(Reload_accountRepository rap) { this.rap = rap; }

    @Override
    public ResponseEntity<ToJsonData> save(Reload_account ra) {
        try {
            Reload_account account = rap.findReload_accountByIdclientOrderByIdDesc(ra.getIdclient().getId());
            if (account != null) {
                if (account.getEst_valider()==0) {
                    throw new Exception("can't reload cause efa nanao demande");
                }
            }

            rap.save(ra);
            return new ResponseEntity<>(new ToJsonData("success",null), HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> selectAll() {
        return new ResponseEntity<>(new ToJsonData(rap.findReload_accountByEst_valider(),null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToJsonData> deleteById(int id) {
        try {
            rap.findById(id).orElseThrow(()->new RessourceNotFoundException("Reload_account","id",id));
            rap.deleteById(id);
            return new ResponseEntity<>(new ToJsonData("delete success",null), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> delete(int id) {
        HttpStatus stat = HttpStatus.OK;
        ToJsonData<String, Object> check = new ToJsonData<>("delete reussi", null);
        try {
            rap.findById(id).orElseThrow(()->new RessourceNotFoundException("Reload_acount","id",id));
            rap.deleteById(id);
        }catch (RessourceNotFoundException e){
            check = new ToJsonData<>(null, e.getMessage());
            stat = HttpStatus.NOT_FOUND;
        }
        return  new ResponseEntity<ToJsonData>(check,stat);
    }

    @Override
    public ResponseEntity<ToJsonData> update(Reload_account ra, int id) {
        ToJsonData<String, Object> check = new ToJsonData<>("update reussi", null);
        HttpStatus stat = HttpStatus.OK;

        try {
            Reload_account reload_account = rap.findById(id).orElseThrow(()->new RessourceNotFoundException("Reload_account","id",id));
            reload_account.setId(id);
            reload_account.setIdclient(ra.getIdclient());
            reload_account.setMontant(ra.getMontant());
            reload_account.setEst_valider(ra.getEst_valider());
            reload_account.setDate_demande(ra.getDate_demande());
            rap.save(reload_account);
        }catch (RessourceNotFoundException e){
            check = new ToJsonData<>(null, e.getMessage());
            stat = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<ToJsonData>(check,stat);
    }

    @Override
    public ResponseEntity<ToJsonData> findAllById(int status) {
        try {
            return new ResponseEntity<>(new ToJsonData(rap.findReload_accountById(status),null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ToJsonData> getOneById(int id) {
        return null;
    }
}
