package com.my.ws_encheres.controller;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.enchere.Enchere;
import com.my.ws_encheres.model.enchere.EnchereCli;
import com.my.ws_encheres.service.EnchereCliService;
import com.my.ws_encheres.service.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enchere/cli/enchere")
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
public class EnchereController {
    private final EnchereService service;
    private final EnchereCliService cli;

    public EnchereController(EnchereService service, EnchereCliService cli) {
        this.service = service;
        this.cli = cli;
    }
    @PostMapping("/create")
    public ResponseEntity<ToJsonData> create(@RequestBody Enchere enchere){
        return service.createEnchere(enchere);
    }
    @GetMapping("/select")
    public ResponseEntity<ToJsonData> SelectAllEnchere(){
        return service.selectAll();
    }
    @GetMapping("/select/{idclient}")
    public ResponseEntity<ToJsonData> getbyIdclient(@PathVariable("idclient") int idclient){
        return service.selectClientEnchere(idclient);
    }
    @PostMapping("/encherir")
    public ResponseEntity<ToJsonData> encherir(@RequestBody EnchereCli enc,@RequestParam("token") String token){
        return cli.Rencherir(enc,token);
    }
    @GetMapping("/historique/{id}")
    public ResponseEntity<ToJsonData> Historique(@PathVariable("id") int idclient){
        return service.selectClientEnchereFinished(idclient);
    }
}
