package com.csc366.project.entity;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    private String name;

    protected Ingredient() {}

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Ingredient.class.getSimpleName() + "[" , "]");
        sj.add(name);
        return sj.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}