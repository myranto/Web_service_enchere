package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.PhotoRepository;
import com.my.ws_encheres.model.Photo;
import com.my.ws_encheres.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhotoImplementService implements PhotoService
{
        private final PhotoRepository pho ;

    public PhotoImplementService(PhotoRepository pho) {
        this.pho = pho;
    }

    @Override
    public ResponseEntity<ToJsonData> save(Photo photo) {
        try{
            pho.save(photo);
            return new ResponseEntity<>(new ToJsonData("success",null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ToJsonData> selectAll() {
        return new ResponseEntity<>(new ToJsonData(pho.findAll(),null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToJsonData> findAllById(int id) {
        try{
            return new ResponseEntity<>(new ToJsonData(pho.findAllByIdEnchere(id),null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
