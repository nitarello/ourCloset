package com.xim.ourcloset.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(name="imgurl")
    private String imgurl;

    @ManyToOne
    @JoinColumn(name="id_fk_collezione")
    @JsonBackReference
    private Collezione collezione;


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


    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
