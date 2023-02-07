package tech.readone.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "article")
public class Article extends PanacheEntityBase {
    // pakai entity base kalau ingin menentukan id nya sendiri
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String tittle;
    public String body;

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    // cascade sama orphan removal buat hapus childreanya
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true) // jika ada error
                                                                                         // lazyinitialization bisa
                                                                                         // gunaka
    // eiger ini
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    // @OneToMany(mappedBy = "article")
    public List<Comment> comments;

    // hati hati dalam pemasukan data dalam penulisan ini soalnya contoh
    // penulisannya colom dihapus baru ini bisa di masukan, setelah itu baru
    // tambahkan masukan kolom dibagian post kolom tapi bakal langsung ambil id nya
    // yang 2, jadi misal kalau mau di tambahkan artikel langsung masuk kei id 3,
    // walaupun masih ada pesan error tapi masih bisa tetep masuk

    // awajib artikelnya baru komentar//kalau gak pakai forloop

}
