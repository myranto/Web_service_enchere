package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.MyExecption.RessourceNotFoundException;
import com.my.ws_encheres.Repository.CompteRepository;
import com.my.ws_encheres.Repository.EnchereCliRepo;
import com.my.ws_encheres.Repository.EnchereRepository;
import com.my.ws_encheres.Repository.TokenRepository;
import com.my.ws_encheres.model.Compte;
import com.my.ws_encheres.model.enchere.Enchere;
import com.my.ws_encheres.model.enchere.EnchereCli;
import com.my.ws_encheres.service.CompteService;
import com.my.ws_encheres.service.EnchereCliService;
import com.my.ws_encheres.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EncherCliImplementService implements EnchereCliService {
    private final EnchereCliRepo service;
    private final EnchereRepository rep;
    private final TokenService serve;
    private final CompteRepository cpt;

    public EncherCliImplementService(EnchereCliRepo service, EnchereRepository rep, TokenService serve, CompteRepository cpt) {
        this.service = service;
        this.rep = rep;
        this.serve = serve;
        this.cpt = cpt;
    }

    @Override
    public ResponseEntity<ToJsonData> Rencherir(EnchereCli cli,String token) {
        try {
            serve.checkTokens(token,cli.getIdclient().getId());
            EnchereCli las = service.findTopByIdenchereOrderByMontantDesc(cli.getIdenchere());
            Enchere enchere = rep.findById(cli.getIdenchere()).orElseThrow(()->new RessourceNotFoundException("enchere","id",cli.getIdenchere()));
            if (enchere.getIdclient().getId()==cli.getIdclient().getId())
                throw new Exception("vous  ne pouvez pas encherir car vous etes le proprio");
            if (las == null) {
                if (enchere.getPrix_mise_enchere()>=cli.getMontant()) {
                    throw new Exception("montant trop basse ..doit etre superieur a "+enchere.getPrix_mise_enchere());
                }
            }
            else{
                if (las.getMontant()>=cli.getMontant())
                    throw new Exception("montant trop basse ..doit etre superieur a "+las.getMontant());
            }
            Compte account = cpt.findCompteByIdclientOrderByIdDescLimit1(cli.getIdclient().getId());
            if (account.getVola()<cli.getMontant()) {
                throw new Exception("votre compte est insuffisant "+account.getVola());
            }
            account.setVola(account.getVola()-cli.getMontant());
            cpt.save(account);
            service.save(cli);
            return new ResponseEntity<>(new ToJsonData("action with success",null), HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
