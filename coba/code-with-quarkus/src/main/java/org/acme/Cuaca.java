package org.acme;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuaca")
public class Cuaca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(length = 80)
    public String kota;

    @Column(name = "suhu_low")
    public Integer suhuLow;

    @Column(name = "suhu_high")
    public Integer suhuHigh;

    public Date tanggal;
}
