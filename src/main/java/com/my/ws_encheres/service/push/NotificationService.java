package com.my.ws_encheres.service.push;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.my.ws_encheres.Repository.ClientRepository;
import com.my.ws_encheres.Repository.EnchereRepository;
import com.my.ws_encheres.Repository.NotificationRepository;
import com.my.ws_encheres.model.Client;
import com.my.ws_encheres.model.enchere.Enchere;
import com.my.ws_encheres.model.enchere.EnchereCli;
import com.my.ws_encheres.model.notif.Fire_token;
import com.my.ws_encheres.model.notif.Notification;
import com.my.ws_encheres.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private final EnchereRepository rep;
    private final ClientRepository use;
    private final NotificationRepository notif;
    private final FirebaseMessagingService firebaseService;
    private final FireService fire;


    public NotificationService(EnchereRepository rep, ClientRepository use, FirebaseMessagingService firebaseService, FireService fire, NotificationRepository notif) {
        this.rep = rep;
        this.use = use;
        this.firebaseService = firebaseService;
        this.fire = fire;
        this.notif = notif;
    }
    String Etat ="enchere avec zero gagnant : montant minimale de vente n'est pas atteinte ";
    Client winner = null;
    int idencheres=-1;
    Client autor = null;

    private void isAtteinte(double montant,double mise) throws Exception {
        if (montant > mise) {
            throw new Exception(Etat);
        }
    }
    public List<Fire_token> getListToken(List<EnchereCli> list,Client autor){
        List<Fire_token> res = new ArrayList<>();
        res.add(fire.findTokenByIdClient(autor.getId()));
        try {
            for (EnchereCli t:list) {
                Fire_token f = fire.findTokenByIdClient(t.getIdclient().getId());
                res.add(f);
            }
        }catch (Exception e){}
        return res;
    }
    public void sendNotification(List<EnchereCli> list,Client autor) throws FirebaseMessagingException {
        List<Fire_token> token = getListToken(list, autor);
            Notification n = new Notification();
            n.setEtat(Etat);
            n.setIdclient(autor.getId());
            n.setMessage("enhere terminer");
            n.setIdenchere(idencheres);
            notif.save(n);
            if (winner!=null)
                n.setWinner(winner.getNom());

        for (Fire_token t:token) {
//            System.out.println(t.getIdclient()+" "+t.getToken());
            try{
            firebaseService.sendNotifupper(Etat,t.getToken());
            }catch (Exception e){

            }
        }
    }
    private void isTerminate() throws FirebaseMessagingException {
        ArrayList<Enchere> list_nontapitra = rep.findEnchereNotFinish();
        for (Enchere e:list_nontapitra) {
            Timestamp tmp = e.getDate_enchere();
            tmp.setHours(tmp.getHours()+e.getDuree());
            Timestamp cur = new Timestamp(System.currentTimeMillis());
            if (cur.compareTo(tmp)>=0) {
                autor = e.getIdclient();
                try {
                    idencheres=e.getId();
//                    System.out.println("size anle izy "+e.getList_rencher().size());
                    winner = e.getList_rencher().get(0).getIdclient();
//                    System.out.println("mise "+e.getPrix_vente()+" m "+e.getList_rencher().get(0).getMontant());
                    isAtteinte(e.getPrix_vente(),e.getList_rencher().get(0).getMontant());
                    Etat = "vainqueur "+e.getDescription()+" est :"+e.getList_rencher().get(0).getIdclient().getNom();
                    sendNotification(e.getList_rencher(),autor);
                }catch (Exception ex){
//                    System.out.println(ex.getMessage());
                    sendNotification(e.getList_rencher(),autor);
//                    ex.printStackTrace();
                }
                e.setStatus(1);
                rep.save(e);
            }
        }
    }
    @Scheduled(fixedRate = 60_000)
    public void runTask() {
        System.out.println("yosh ");
        try {
            isTerminate();
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
