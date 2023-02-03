package com.my.ws_encheres.service.push;

import com.google.firebase.messaging.*;
import com.my.ws_encheres.model.Client;
import com.my.ws_encheres.model.enchere.Enchere;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotification(Client note, String token) throws FirebaseMessagingException {
        String Mess = " votre enchere est terminer ";
        Notification notification = Notification
                .builder()
                .setTitle(note.getNom())
                .setBody("vainqueur : fini")
//                .setImage(note.getImage())
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
//                .putAllData(note.getData())
                .build();
        return firebaseMessaging.send(message);
    }
    public String sendNotifupper(String mess,String token) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle("Auction mobile")
                .setBody(mess)
//                .setImage(note.getImage())
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
//                .putAllData(note.getData())
                .build();
        return firebaseMessaging.send(message);

    }

}
