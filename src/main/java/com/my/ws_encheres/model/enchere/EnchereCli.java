package com.my.ws_encheres.model.enchere;

import com.my.ws_encheres.model.Client;
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
@Table(name = "enchere_client")
public class EnchereCli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional=false)
    @JoinColumn(name="idclient", nullable=false, updatable=false)
    private Client idclient;
    @Column(name = "idenchere",nullable = false)
    private int idenchere;
    @Column(name = "montant",nullable = false)
    private double montant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    public int getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(int idenchere) {
        this.idenchere = idenchere;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
