package com.my.ws_encheres.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reload_account")
public class Reload_account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "idclient", nullable = false)
//    private int idclient;

    @Column(name = "montant", nullable = false)
    private double montant;

    @Column(name = "est_valider", nullable = false)
    private int est_valider=0;
    @ManyToOne(optional=false)
    @JoinColumn(name="idclient", nullable=false, updatable=false)
    private Client idclient;

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    @Column(name = "date_demande", nullable = false)
    private Timestamp date_demande=new Timestamp(System.currentTimeMillis());

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getIdclient() {
//        return idclient;
//    }
//
//    public void setIdclient(int idclient) {
//        this.idclient = idclient;
//    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getEst_valider() {
        return est_valider;
    }

    public void setEst_valider(int est_valider) {
        this.est_valider = est_valider;
    }

    public Timestamp getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(Timestamp date_demande) {
        this.date_demande = date_demande;
    }
}
