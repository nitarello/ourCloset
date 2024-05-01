package com.xim.ourcloset.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="artigiano")
public class Artigiano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idart;


    @OneToMany(mappedBy = "artigiano",fetch = FetchType.EAGER)
    private List<Collezione>collezione;

    @OneToMany(mappedBy = "artigiano")
    private List<User> user;

    private String nomebrand;

    public Artigiano() {
    }

    public Artigiano(List<Collezione> collezione, List<User> user, String nomebrand) {
        this.collezione = collezione;
        this.user = user;
        this.nomebrand = nomebrand;
    }

    public Artigiano(int idart, List<Collezione> collezione, List<User> user, String nomebrand) {
        this.idart = idart;
        this.collezione = collezione;
        this.user = user;
        this.nomebrand = nomebrand;
    }

    public List<Collezione> getCollezione() {
        return collezione;
    }

    public void setCollezione(List<Collezione> collezione) {
        this.collezione = collezione;
    }

    public int getIdart() {
        return idart;
    }

    public void setIdart(int idart) {
        this.idart = idart;
    }



    public String getNomebrand() {
        return nomebrand;
    }

    public void setNomebrand(String nomebrand) {
        this.nomebrand = nomebrand;
    }
}
