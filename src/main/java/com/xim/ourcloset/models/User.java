package com.xim.ourcloset.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int user_id;
   private String nome;
   private String cognome;
    private String propic;
    private Date dataNascita;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private LoginModel loginModel;



    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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