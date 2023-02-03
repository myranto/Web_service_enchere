package com.my.ws_encheres.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.NotificationRepository;
import com.my.ws_encheres.model.Client;
import com.my.ws_encheres.model.notif.Fire_token;
import com.my.ws_encheres.model.notif.Notification;
import com.my.ws_encheres.service.FireService;
import com.my.ws_encheres.service.push.FirebaseMessagingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/enchere/notification")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class NotificationController {

    private final FirebaseMessagingService firebaseService;
    private final NotificationRepository rep;
    private final FireService service;

    public NotificationController(FirebaseMessagingService firebaseService, NotificationRepository rep, FireService service) {
        this.firebaseService = firebaseService;
        this.rep = rep;
        this.service = service;
    }

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Client note,
                                   @RequestParam("token") String topic) throws FirebaseMessagingException {
        return firebaseService.sendNotification(note, topic);
    }
    @GetMapping("{idclient}")
    public ResponseEntity<ToJsonData> findByIdc(@PathVariable("idclient") int idclient){
        List<Notification> list = rep.findAllByIdclientOrderByIdAsc(idclient);
        return new ResponseEntity<ToJsonData>(new ToJsonData<>(list,null),HttpStatus.OK);
    }
    @PostMapping("/save_token")
    public ResponseEntity<ToJsonData> saveToken(@RequestBody Fire_token tok) {
        System.out.println("sousous");
        service.save(tok);
        return new ResponseEntity<ToJsonData>(new ToJsonData<>("success",null),HttpStatus.OK);
    }
}
