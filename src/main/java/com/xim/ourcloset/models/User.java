package com.xim.ourcloset.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userid;
    private String nome;
    private String cognome;
    private String propic;
    private Date dataNascita;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private LoginModel loginModel;

    @OneToMany(mappedBy = "user")
    private List<Carta> carte;

    @ManyToOne
    @JoinColumn(name="id_fk_artigiano")
    private Artigiano artigiano;

    public Artigiano getArtigiano() {
        return artigiano;
    }

    public void setArtigiano(Artigiano artigiano) {
        this.artigiano = artigiano;
    }

    public List<Carta> getCarte() {
        return carte;
    }

    public void setCarte(List<Carta> carte) {
        this.carte = carte;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int user_id) {
        this.userid = user_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic;
    }

    public Date getData_nascita() {
        return dataNascita;
    }

    public void setData_nascita(Date data_nascita) {
        this.dataNascita = data_nascita;
    }






}
