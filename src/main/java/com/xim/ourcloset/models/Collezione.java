package com.xim.ourcloset.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="collezione")
public class Collezione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="nomecol")
    private String nomeCollezione;
    @Column(name="datacreazione")
    private Date dataCreazione;
    @Column(name="luogocreazione")
    private String luogoCreazione;



    @OneToMany(mappedBy = "collezione")

    private List<Modello> modello;

    public List<Modello> getModello() {
        return modello;
    }

    public void setModello(List<Modello> modello) {
        this.modello = modello;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCollezione() {
        return nomeCollezione;
    }

    public void setNomeCollezione(String nomeCollezione) {
        this.nomeCollezione = nomeCollezione;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getLuogoCreazione() {
        return luogoCreazione;
    }

    public void setLuogoCreazione(String luogoCreazione) {
        this.luogoCreazione = luogoCreazione;
    }
}
