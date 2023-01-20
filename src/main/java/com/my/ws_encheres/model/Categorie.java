package com.my.ws_encheres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categorie")
public class Categorie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToMany(targetEntity = Enchere.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "idcategorie",referencedColumnName = "id")
//    private List<Enchere> list_enchere;
    @Column(name = "nom",nullable = false)
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
