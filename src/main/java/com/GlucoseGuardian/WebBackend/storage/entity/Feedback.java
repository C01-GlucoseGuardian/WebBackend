package com.GlucoseGuardian.WebBackend.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Date;

public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 300, nullable = false)
    private String stato_salute;

    @Column(length = 300, nullable = false)
    private String ore_sonno;

    @Column(length = 300, nullable = false)
    private String dolori;

    @Column(length = 300, nullable = false)
    private String svenimenti;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private Time ora;

    public Feedback() {

    }

    public Feedback(int id, String stato_salute, String ore_sonno, String dolori, String svenimenti, Date data, Time ora) {
        this.id = id;
        this.stato_salute = stato_salute;
        this.ore_sonno = ore_sonno;
        this.dolori = dolori;
        this.svenimenti = svenimenti;
        this.data = data;
        this.ora = ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStato_salute() {
        return stato_salute;
    }

    public void setStato_salute(String stato_salute) {
        this.stato_salute = stato_salute;
    }

    public String getOre_sonno() {
        return ore_sonno;
    }

    public void setOre_sonno(String ore_sonno) {
        this.ore_sonno = ore_sonno;
    }

    public String getDolori() {
        return dolori;
    }

    public void setDolori(String dolori) {
        this.dolori = dolori;
    }

    public String getSvenimenti() {
        return svenimenti;
    }

    public void setSvenimenti(String svenimenti) {
        this.svenimenti = svenimenti;
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

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", stato_salute='" + stato_salute + '\'' +
                ", ore_sonno='" + ore_sonno + '\'' +
                ", dolori='" + dolori + '\'' +
                ", svenimenti='" + svenimenti + '\'' +
                ", data=" + data +
                ", ora=" + ora +
                '}';
    }
}
