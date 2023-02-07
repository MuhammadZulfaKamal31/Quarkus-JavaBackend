package tech.readone.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Task extends PanacheEntity {

    @NotBlank
    public String tittle;
    public String description;
    public String subject;
    public Integer score;

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }
}
