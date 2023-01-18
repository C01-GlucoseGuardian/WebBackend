package com.GlucoseGuardian.WebBackend.storage.entity;

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

}
