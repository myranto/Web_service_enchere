package com.my.ws_encheres.model.enchere;

import com.my.ws_encheres.model.Categorie;
import com.my.ws_encheres.model.Client;
import com.my.ws_encheres.model.Photo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enchere")
public class Enchere  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_enchere")
//    @Transient
    private Timestamp date_enchere = new Timestamp(System.currentTimeMillis());
    @ManyToOne(optional=false)
    @JoinColumn(name="idclient", nullable=false, updatable=false)
    private Client idclient;

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }
    @Column(name = "prix_vente", nullable = false)
    private double prix_vente;
    @Column(name = "prix_mise_enchere", nullable = false)
    private double prix_mise_enchere;
//    @Column(name = "idcategorie", nullable = false)
//    private int idcategorie;
    @ManyToOne(optional=false)
    @JoinColumn(name="idcategorie", nullable=false, updatable=false)
    private Categorie idcategorie;
    @OneToMany(targetEntity = EnchereCli.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idenchere",referencedColumnName = "id")
    private List<EnchereCli> list_rencher;

    public List<EnchereCli> getList_rencher() {
        return list_rencher;
    }

    public void setList_rencher(List<EnchereCli> list_rencher) {
        this.list_rencher = list_rencher;
    }

    @Column(name = "duree", nullable = false)
    private int duree;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status")
    private int status=0;

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate_enchere() {
        return date_enchere;
    }

    public void setDate_enchere(Timestamp date_enchere) {
        this.date_enchere = date_enchere;
    }


    public double getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(double prix_vente) {
        this.prix_vente = prix_vente;
    }

    public double getPrix_mise_enchere() {
        return prix_mise_enchere;
    }

    public void setPrix_mise_enchere(double prix_mise_enchere) {
        this.prix_mise_enchere = prix_mise_enchere;
    }


    public int getDuree() {
        return duree;
    }

    public void setDuree(int dure) {
        this.duree = dure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }
//    public int getIdcategorie() {
//        return idcategorie;
//    }
//
//    public void setIdcategorie(int idcategorie) {
//        this.idcategorie = idcategorie;
//    }
}
