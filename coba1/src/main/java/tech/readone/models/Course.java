package tech.readone.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "kursus")
public class Course extends PanacheEntityBase {

    @Id // kalau gak di kasih akan eror identityfier
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String tittle;

    // many to many lebih baik gak pakai cascade
    // masukan EIGER supaya gak error kalau studen resouce di masukan nilai 2 kali
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // kalau pakai many to many buatnya tuh pakai table
    // baru
    // cara hubungin buat table baru, usahakan kalau bikin di foreign key ada
    // referensinya
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    public List<Student> students;
}
