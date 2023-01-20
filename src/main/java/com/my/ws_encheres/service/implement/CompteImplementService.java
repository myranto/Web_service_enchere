package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.CompteRepository;
import com.my.ws_encheres.Repository.Reload_accountRepository;
import com.my.ws_encheres.model.Compte;
import com.my.ws_encheres.model.Reload_account;
import com.my.ws_encheres.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompteImplementService implements CompteService {
    private final CompteRepository rep;
    private final Reload_accountRepository rep_cli;

    public CompteImplementService(CompteRepository rep, Reload_accountRepository rep_cli) {
        this.rep = rep;
        this.rep_cli = rep_cli;
    }

    @Override
    public ResponseEntity<ToJsonData> getAccount(int idclient) {
        try {
            Compte client = rep.findCompteByIdclientOrderByIdDescLimit1(idclient);
            if (client==null)
                throw new Exception("account not found for id:"+idclient);
            return new ResponseEntity<ToJsonData>(new ToJsonData(client,null), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> Reload_Account(int idclient) {
        try {
            System.out.println(idclient);
            Reload_account account = rep_cli.findReload_accountByIdclientOrderByIdDesc(idclient);
            Compte client = rep.findCompteByIdclientOrderByIdDescLimit1(idclient);
            if (account==null)
                throw new Exception("account not found for id:"+idclient);
            if (client==null)
                throw new Exception("account not found for id:"+idclient);
            client.setVola(client.getVola()+ account.getMontant());
            rep.save(client);
            account.setEst_valider(1);
            rep_cli.save(account);
            return new ResponseEntity<ToJsonData>(new ToJsonData<>("account reload with success",null), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
