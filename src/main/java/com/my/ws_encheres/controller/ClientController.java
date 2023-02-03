package com.my.ws_encheres.controller;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.fiche.RequestSearch;
import com.my.ws_encheres.model.Client;
import com.my.ws_encheres.model.enchere.Enchere;
import com.my.ws_encheres.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enchere/cli")
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
public class ClientController {
    private final ClientService serve;
    private final TokenService tok_service;
    private final SearchEnchere search;
    private final EnchereService s;
    private final CompteService account;
    public ClientController(ClientService serve, TokenService tok_service, CompteService account, SearchEnchere search, EnchereService s) {
        this.serve = serve;
        this.tok_service = tok_service;
        this.account = account;
        this.search = search;
        this.s = s;
    }
//    verification de la validited du client (connexion expirer ou non)
    @GetMapping("/checkToken/{id}/{token}")
    public ResponseEntity<ToJsonData> isConnected(@PathVariable("id") int id, @PathVariable("token") String token){
        try {
//            s.isTerminate();
            tok_service.checkTokens(token,id);
            return new ResponseEntity<>(new ToJsonData<>("is connected true",null),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }
//    se connecter en tant que client
    @PostMapping("/login")
    public ResponseEntity<ToJsonData> login(@RequestBody Client cli) {
        return serve.login(cli);
    }
//    se deconnecter
    @DeleteMapping("/log_out/{id}")
    public ResponseEntity<ToJsonData>LogOut(@PathVariable("id") int id){
        try {

            return serve.Logout(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/inscription")
    public ResponseEntity<ToJsonData> Inscription(@RequestBody Client cli){

        return  serve.creates(cli);
    }
//    voir compte client
    @GetMapping("/account/{id}")
    public ResponseEntity<ToJsonData> getAccountCli(@PathVariable("id") int idclient){
//        s.isTerminate();

        return account.getAccount(idclient);
    }

    @PostMapping("/search")
    public ResponseEntity<ToJsonData> SearchAvance(@RequestBody RequestSearch enchere){
//        s.isTerminate();
        return search.searchAdvanced(enchere);
    }

}
