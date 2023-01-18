package com.GlucoseGuardian.WebBackend.storage.entity;

import jakarta.persistence.*;

@Entity
public class Farmaco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UNSIGNED INT")
    private int id;

    @Column(columnDefinition = "VARCHAR(50)")
    private String nome_farmaco;
    @Column(columnDefinition = "VARCHAR(255)")
    private String principio_attivo;
    @Column(columnDefinition = "VARCHAR(255)")
    private String confezione;


    public Farmaco() {
    }

    public Farmaco(String nome_farmaco, String principio_attivo, String confezione) {
        this.nome_farmaco = nome_farmaco;
        this.principio_attivo = principio_attivo;
        this.confezione = confezione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_farmaco() {
        return nome_farmaco;
    }

    public void setNome_farmaco(String nome_farmaco) {
        this.nome_farmaco = nome_farmaco;
    }

    public String getPrincipio_attivo() {
        return principio_attivo;
    }

    public void setPrincipio_attivo(String principio_attivo) {
        this.principio_attivo = principio_attivo;
    }

    public String getConfezione() {
        return confezione;
    }

    public void setConfezione(String confezione) {
        this.confezione = confezione;
    }

    @Override
    public String toString() {
        return "Farmaco{" + "id=" + id + ", nome_farmaco='" + nome_farmaco + '\'' + ", principio_attivo='" + principio_attivo + '\'' + ", confezione='" + confezione + '\'' + '}';
    }
}
