package com.xim.ourcloset.models;

import jakarta.persistence.*;

@Entity
@Table(name="carte")
public class carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_fk_modello")
    private Modello modello;


    @ManyToOne
    @JoinColumn(name="id_fk_user")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modello getModello() {
        return modello;
    }

    public void setModello(Modello modello) {
        this.modello = modello;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
