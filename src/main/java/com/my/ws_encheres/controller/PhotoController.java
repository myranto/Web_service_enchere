package com.my.ws_encheres.controller;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.model.Categorie;
import com.my.ws_encheres.model.Photo;
import com.my.ws_encheres.service.PhotoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enchere/client/photo")
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
public class PhotoController {
   private final PhotoService photos;

    public PhotoController(PhotoService photos) {
        this.photos = photos;
    }

    @PostMapping
    public ResponseEntity<ToJsonData> create(@RequestBody Photo p) {
        return photos.save(p);
    }
    @GetMapping
    public ResponseEntity<ToJsonData> get_all(){
        return photos.selectAll();
    }

    @GetMapping("{id}")
        public ResponseEntity<ToJsonData> findAllbyID(@PathVariable(value = "id") int id){
        return photos.findAllById(id);
    }
}
