package com.GlucoseGuardian.WebBackend.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ProfiloTutore implements Serializable {
    @Id
    @Column(columnDefinition = "CHAR(16)")
    private String codice_fiscale;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String nome;
    @Column(columnDefinition = "VARCHAR(30)", nullable = false)

    private String cognome;
    @Column(nullable = false)
    private Date data_nascita;
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String indirizzo;
    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    private String telefono;
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;
    @Column(columnDefinition = "CHAR(1)", nullable = false)
    private char sesso;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String totp_key;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String relazione_di_parentela;

    public ProfiloTutore() {
    }

    public ProfiloTutore(String codice_fiscale, String nome, String cognome, Date data_nascita, String indirizzo, String telefono, String email, String password, char sesso, String totp_key, String relazione_di_parentela) {
        this.codice_fiscale = codice_fiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.sesso = sesso;
        this.totp_key = totp_key;
        this.relazione_di_parentela = relazione_di_parentela;
    }

    @Override
    public String toString() {
        return "ProfiloTutore{" + "codice_fiscale='" + codice_fiscale + '\'' + ", nome='" + nome + '\'' + ", cognome='" + cognome + '\'' + ", data_nascita=" + data_nascita + ", indirizzo='" + indirizzo + '\'' + ", telefono='" + telefono + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", sesso=" + sesso + ", totp_key='" + totp_key + '\'' + ", relazione_di_parentela='" + relazione_di_parentela + '\'' + '}';
    }
}
