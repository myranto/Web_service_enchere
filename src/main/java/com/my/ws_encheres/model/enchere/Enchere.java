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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    @OneToMany(targetEntity = EnchereCli.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "idenchere",referencedColumnName = "id")
    private List<EnchereCli> list_rencher;

    public List<EnchereCli> getList_rencher() {
        try {
            System.out.println("size o "+list_rencher.size());
        Collections.sort(list_rencher,Comparator.comparing(EnchereCli::getMontant).reversed());

        }catch (Exception e){
//            e.printStackTrace();
        }
        return list_rencher;
    }


    @Column(name = "duree", nullable = false)
    private int duree;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status")
    private int status=0;

}
