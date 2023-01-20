package com.my.ws_encheres.controller;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Reload_account;
import com.my.ws_encheres.service.EnchereService;
import com.my.ws_encheres.service.Reload_accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enchere/cli/Reload_acccount")
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
public class Reload_accountController {

    private final Reload_accountService service;
    private final EnchereService s;
    public Reload_accountController(Reload_accountService service, EnchereService s) { this.service = service;
        this.s = s;
    }

    @PostMapping
    public ResponseEntity<ToJsonData> create(@RequestBody Reload_account rap){
        s.isTerminate();
        return service.save(rap);
    }
    @GetMapping
    public ResponseEntity<ToJsonData> get_all(){
        s.isTerminate();
        return service.selectAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<ToJsonData> findAllbyReloadAccount(@PathVariable("id")int id){
        s.isTerminate();
        return service.findAllById(id);
    }
}
