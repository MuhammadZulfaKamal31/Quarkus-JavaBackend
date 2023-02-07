package org.acme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
@NamedQuery(name = "Task.getAllTasks", query = " SELECT t from Task t")
@NamedQuery(name = "Task.getTaskById", query = "SELECT t FROM Task t WHERE t.id = :uy")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String tittle;

    public String description;

    @Column(name = "mata_pelajaran")
    public String mataPelajaran;

    public Integer nilai;
}
