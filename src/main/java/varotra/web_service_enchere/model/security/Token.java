package varotra.web_service_enchere.model.security;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Random;

@Data
@Entity
@Table(name = "token")
public class Token {

    @Id
    private int id;
    @Column(name = "token",nullable = false)
    private String tok;
    @Column(name = "idclient",nullable = false)
    private int idclient;


    public Token(int id, String tok, int idclient) {
        this.id = id;
        this.tok = tok;
        this.idclient = idclient;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    @Column(name = "dateexpiration",nullable = false)
    private Timestamp date=null;

    public String getTok() {
        return tok;
    }

    public void setTok(String tok) {
        this.tok = tok;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Token() {
    }

    public Token(int id, String tok) {
        this.id = id;
        this.tok = tok;
    }

    public Token(int id, String tok, Timestamp date) {
        this.id = id;
        this.tok = tok;
        this.date = date;
    }

    public static int Rand(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
//    generate token


    public void setId(int id) {
        this.id = id;
    }

//    check token

    public int getId() {
        return id;
    }
//    insert into admin(nom,email,pwd) values ('moi','moi@gmail.com','mdp1');
//    public static void main(String[] args) throws Exception {
//        Token tok = new Token();
//        tok.setId(1);
//        tok.saveToken(1,"mdp1");
//    }

}
