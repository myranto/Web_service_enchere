package com.my.ws_encheres.model.notif;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idclient",nullable = false)
    private int idclient;
    @Column(name = "idenchere",nullable = false)
    private int idenchere;
    @Column(name = "winner")
    private String winner="person";
    @Column(name = "message",nullable = false)
    private String message;
    @Column(name = "etat",nullable = false)
    private String etat;
}
