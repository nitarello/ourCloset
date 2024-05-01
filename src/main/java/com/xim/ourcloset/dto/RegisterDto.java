package com.xim.ourcloset.dto;

import com.xim.ourcloset.models.Artigiano;

import java.sql.Date;

public class RegisterDto {
    // Dati da User
    private String nome;
    private String cognome;
    private String propic;
    private Date dataNascita;

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    // Dati da LoginModel
    private String username;
    private String password;

    // Getters e Setters per User
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



    // Getters e Setters per LoginModel
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //campi per ARTIGIANO EVENTUALE


}