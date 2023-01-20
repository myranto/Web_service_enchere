package com.my.ws_encheres.model.statistique;

import com.my.ws_encheres.model.Categorie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "v_finale")
@Immutable
public class Statistique {
    @Id
    @Column(name = "nom",insertable = false,updatable = false)
    private String nom;
    @ManyToOne(optional=false)
    @JoinColumn(name="idcategorie", insertable=false, updatable=false)
    private Categorie idcategorie;
    @Column(name = "total",insertable = false,updatable = false)
    private double total;
    @Column(name = "date_encheres",insertable = false,updatable = false)
    private Date date_encheres;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate_encheres() {
        return date_encheres;
    }

    public void setDate_encheres(Date date_encheres) {
        this.date_encheres = date_encheres;
    }
}
