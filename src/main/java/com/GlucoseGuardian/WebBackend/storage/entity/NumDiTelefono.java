package com.GlucoseGuardian.WebBackend.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class NumDiTelefono {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 15, nullable = false)
    private String numero;

    public NumDiTelefono() {

    }

    public NumDiTelefono(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "NumDiTelefono{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                '}';
    }
}
