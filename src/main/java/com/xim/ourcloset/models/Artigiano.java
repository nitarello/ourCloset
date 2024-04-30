package com.xim.ourcloset.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="artigiano")
public class Artigiano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idart;
    @OneToMany(mappedBy = "artigiano")
    private List<Modello> modello;

    private String nomebrand;


    public int getIdart() {
        return idart;
    }

    public void setIdart(int idart) {
        this.idart = idart;
    }

    public List<Modello> getModello() {
        return modello;
    }

    public void setModello(List<Modello> modello) {
        this.modello = modello;
    }

    public String getNomebrand() {
        return nomebrand;
    }

    public void setNomebrand(String nomebrand) {
        this.nomebrand = nomebrand;
    }
}
