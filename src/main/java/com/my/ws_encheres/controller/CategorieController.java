package com.my.ws_encheres.controller;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Categorie;
import com.my.ws_encheres.service.CategorieService;
import com.my.ws_encheres.service.EnchereService;
import com.my.ws_encheres.service.implement.EnchereImplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enchere/admin/categorie")
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
public class CategorieController {
    private final CategorieService service;
    private final EnchereService s;
    public CategorieController(CategorieService service, EnchereService s) {
        this.service = service;
        this.s = s;
    }

//    creation de categorie
    @PostMapping
    public ResponseEntity<ToJsonData> create(@RequestBody Categorie catego){
        s.isTerminate();
            return service.save(catego);
    }
    @PutMapping("{idc}/{name}")
    public ResponseEntity<ToJsonData> update(@PathVariable("idc") int toUp,@PathVariable("name") String name){
        s.isTerminate();

        return service.update(toUp,name);
    }
//    select all categorie from database
    @GetMapping
    public ResponseEntity<ToJsonData> get_all(){
        s.isTerminate();

        return service.selectAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ToJsonData> findAllbyID(@PathVariable("id")int id){
        s.isTerminate();

        return service.findAllById(id);
    }
}
