package com.my.ws_encheres.controller;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.StatistiqueRepository;
import com.my.ws_encheres.service.EnchereService;
import com.my.ws_encheres.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enchere/stat")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class StatController {
    private final StatistiqueService rep;
    private final EnchereService s;
    public StatController(StatistiqueService rep, EnchereService s) {
        this.rep = rep;
        this.s = s;
    }
    @GetMapping("/select")
    public ResponseEntity<ToJsonData> stat(){
        s.isTerminate();
        return rep.getStat();
    }
}
