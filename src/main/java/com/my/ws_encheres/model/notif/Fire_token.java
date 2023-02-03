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
@Table(name = "Fire_token")
public class Fire_token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idclient",nullable = false)
    private int idclient;
    @Column(name = "token",nullable = false)
    private String token;
}
