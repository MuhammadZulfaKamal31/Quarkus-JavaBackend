package tech.readone.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "adress")
public class Adress extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String provinsi;
    public String city;
    public String district;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // cuman gini aja hasilnya error looping
    public User user;

    @JsonIgnore // untuk cara mengatasinya pakai json ignore ini
    public User getUser() {
        return user;
    }

    @JsonAnySetter
    public void setUser(User user) {
        this.user = user;
    }

}
