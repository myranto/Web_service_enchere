package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.PhotoRepository;
import com.my.ws_encheres.fiche.EnchereParam;
import com.my.ws_encheres.fiche.RequestSearch;
import com.my.ws_encheres.model.Photo;
import com.my.ws_encheres.model.enchere.Enchere;
import com.my.ws_encheres.service.SearchEnchere;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchImplementService implements SearchEnchere {
    @PersistenceContext
    EntityManager entity;
    private final PhotoRepository pho ;

    public SearchImplementService(PhotoRepository pho) {
        this.pho = pho;
    }


    @Override
    public List<Enchere> searchByKeyWord(String key) {
        String sql = "select * from Enchere   where upper(description) like upper('%"+key+"%')";
        System.out.println(sql);
        Query query = entity.createNativeQuery(sql, Enchere.class);
        List<Enchere> list = query.getResultList();
        return list;
    }

    @Override
    public ResponseEntity<ToJsonData> searchAdvanced(RequestSearch o) {
        try {
            Query query = entity.createNativeQuery(o.createQuery(), Enchere.class);
            List<Enchere> list = query.getResultList();
            ArrayList<EnchereParam> finale = new ArrayList<>();
            for (Enchere l:list) {
                ArrayList<Photo> list_pic = pho.findAllByIdEnchere(l.getId());
                finale.add(new EnchereParam(l,list_pic));
            }
            return new ResponseEntity<ToJsonData>(new ToJsonData(finale,null), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
