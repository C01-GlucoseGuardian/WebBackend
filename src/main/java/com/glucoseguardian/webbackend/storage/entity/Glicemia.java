package com.glucoseguardian.webbackend.storage.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Classe che rappresenta un'istanza dell'entity Glicemia.
 */
@Entity
public class Glicemia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UNSIGNED INT", nullable = false)
    private int id;
    @Column(nullable = false)
    private int livelloGlucosio;
    @Column(nullable = false)
    private Date data;
    @Column(nullable = false)
    private Time ora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivelloGlucosio() {
        return livelloGlucosio;
    }

    public void setLivelloGlucosio(int livelloGlucosio) {
        this.livelloGlucosio = livelloGlucosio;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public Glicemia(int livelloGlucosio, Date data, Time ora) {
        this.livelloGlucosio = livelloGlucosio;
        this.data = data;
        this.ora = ora;
    }

    public Glicemia() {
    }

    @Override
    public String toString() {
        return "Glicemia{" +
                "id=" + id +
                ", livelloGlucosio=" + livelloGlucosio +
                ", data=" + data +
                ", ora=" + ora +
                '}';
    }
}
