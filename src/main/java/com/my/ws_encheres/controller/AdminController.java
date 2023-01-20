package com.my.ws_encheres.controller;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Admin;
import com.my.ws_encheres.model.commission.Commission;
import com.my.ws_encheres.model.enchere.Duration;
import com.my.ws_encheres.service.AdminService;
import com.my.ws_encheres.service.CommissionService;
import com.my.ws_encheres.service.CompteService;
import com.my.ws_encheres.service.DurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enchere/admin")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class AdminController {
    private final AdminService service;
    private final CompteService account;
    private final CommissionService com;
    private final DurationService dur;

    public AdminController(AdminService service, CompteService account, DurationService dur, CommissionService com) {
        this.service = service;
        this.account = account;
        this.dur = dur;
        this.com = com;
    }

//    creation admin
    @PostMapping("/save")
    public ResponseEntity<ToJsonData> create(@RequestBody Admin toCreate) {
        return service.creates(toCreate);
    }

//    se connecter en tant qu'admin
    @PostMapping("/login")
    public ResponseEntity<ToJsonData> login(@RequestBody Admin admin) {
        return service.login(admin);
    }
//validation account et rechargement de compte client
    @GetMapping("/account/{idclient}")
    public ResponseEntity<ToJsonData> Reload_Account(@PathVariable("idclient") int idclient) {
        return account.Reload_Account(idclient);
    }
    @PostMapping("/duration/create")
    public ResponseEntity<ToJsonData> createDefaultDuration(@RequestBody Duration dure){
        return dur.create(dure);
    }
    @GetMapping("/duration/last")
    public ResponseEntity<ToJsonData> getLastDuration(){
        return dur.findDesc();
    }
    @PostMapping("/commission/create")
    public ResponseEntity<ToJsonData> createCommission(@RequestBody Commission toCom){
        return com.create(toCom);
    }
    @GetMapping("/commission/last")
    public ResponseEntity<ToJsonData> lastCommission(){
        return com.findLastCommission();
    }
}
