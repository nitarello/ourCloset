package com.xim.ourcloset.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="modello")
public class Modello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmodello;

    public int getIdmodello() {
        return idmodello;
    }

    public void setIdmodello(int idmodello) {
        this.idmodello = idmodello;
    }

    @Column(name="nomemodello")
    private String nomemodello;


    @ManyToOne
    @JoinColumn(name="id_fk_collezione")
    @JsonIgnore
    private Collezione collezione;

    @ManyToOne
    @JoinColumn(name="id_fk_artigiano")
    @JsonIgnore
    private Artigiano artigiano;



    public String getNomemodello() {
        return nomemodello;
    }

    public void setNomemodello(String nomemodello) {
        this.nomemodello = nomemodello;
    }

    public Collezione getCollezione() {
        return collezione;
    }

    public void setCollezione(Collezione collezione) {
        this.collezione = collezione;
    }

    public Artigiano getArtigiano() {
        return artigiano;
    }

    public void setArtigiano(Artigiano artigiano) {
        this.artigiano = artigiano;
    }
}
