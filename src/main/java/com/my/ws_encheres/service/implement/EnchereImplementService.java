package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.DurationRepository;
import com.my.ws_encheres.Repository.EnchereRepository;
import com.my.ws_encheres.Repository.PhotoRepository;
import com.my.ws_encheres.fiche.EnchereParam;
import com.my.ws_encheres.model.Photo;
import com.my.ws_encheres.model.enchere.Enchere;
import com.my.ws_encheres.service.EnchereService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class EnchereImplementService implements EnchereService {
    private final EnchereRepository rep;
    private final DurationRepository dur;
    private final PhotoRepository pho ;


    public EnchereImplementService(EnchereRepository rep, DurationRepository dur, PhotoRepository pho) {
        this.rep = rep;
        this.dur = dur;
        this.pho = pho;
    }

    @Override
    public ResponseEntity<ToJsonData> createEnchere(Enchere enchere) {
        try {
//            isTerminate();
            if (enchere.getDuree()==0)
                enchere.setDuree(dur.findDurationByIdDesc().getValue());
            rep.save(enchere);
            return new ResponseEntity<>(new ToJsonData("enchere created with success",null), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> createEnchereReal(EnchereParam enchere) {
        try {
//            isTerminate();
            if (enchere.getEnchere().getDuree()==0)
                enchere.getEnchere().setDuree(dur.findDurationByIdDesc().getValue());
            rep.save(enchere.getEnchere());
            for (int i = 0; i < enchere.getPic().size(); i++) {
                enchere.getPic().get(i).setIdEnchere(enchere.getEnchere().getId());
            }
            pho.saveAll(enchere.getPic());
            return new ResponseEntity<>(new ToJsonData("enchere created with success",null), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> selectAll() {
//        isTerminate();
        ArrayList<Enchere> list = rep.findEnchereNotFinish();
        ArrayList<EnchereParam> finale = new ArrayList<>();
        for (Enchere l:list) {
            ArrayList<Photo> list_pic = pho.findAllByIdEnchere(l.getId());
            finale.add(new EnchereParam(l,list_pic));
        }
        return new ResponseEntity<>(new ToJsonData(finale,null),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToJsonData> selectClientEnchere(int idclient) {
        try {
//            isTerminate();
            ArrayList<Enchere> list = rep.findEncheresClient(idclient);
            if (list == null) {
                throw new Exception("client id:"+idclient+" doesn't have enchere");
            }
            return new ResponseEntity<>(new ToJsonData(list,null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> selectClientEnchereReal(int idclient) {
        try {
//            isTerminate();
            ArrayList<Enchere> list = rep.findEncheresClient(idclient);
            ArrayList<EnchereParam> finale = new ArrayList<>();
            if (list == null) {
                throw new Exception("client id:"+idclient+" doesn't have enchere");
            }
            for (Enchere l:list) {
                ArrayList<Photo> list_pic = pho.findAllByIdEnchere(l.getId());
                finale.add(new EnchereParam(l,list_pic));
            }
            return new ResponseEntity<>(new ToJsonData(finale,null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> selectClientEnchereFinished(int idclient) {
        try {
//            isTerminate();
            ArrayList<Enchere> list = rep.findEnchereByIdclientAndStatusEquals(idclient);
            System.out.println(list.get(0).getDate_enchere());
            if (list == null) {
                throw new Exception("client id:"+idclient+" doesn't have enchere");
            }
            return new ResponseEntity<>(new ToJsonData(list,null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> findByEnchere(int idenchere) {
        try {
//            isTerminate();
            Enchere list = rep.findTopById(idenchere);

            if (list == null) {
                throw new Exception("Not enchere found");
            }
                ArrayList<Photo> list_pic = pho.findAllByIdEnchere(list.getId());
                EnchereParam finale= new EnchereParam(list,list_pic);
            return new ResponseEntity<>(new ToJsonData(finale,null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void isTerminate() {
//        ArrayList<Enchere> list_nontapitra = rep.findEnchereNotFinish();
//        for (Enchere e:list_nontapitra) {
//            Timestamp tmp = e.getDate_enchere();
//            tmp.setHours(tmp.getHours()+e.getDuree());
//            Timestamp cur = new Timestamp(System.currentTimeMillis());
//            if (cur.compareTo(tmp)>=0) {
//                System.out.println(cur+" current");
//                System.out.println(tmp+" io e");
//                e.setStatus(1);
//                rep.save(e);
//            }
//        }
    }


}
