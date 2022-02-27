package com.csc366.project.entity;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prepared")
public class Prepared extends Product {
    private Long calories;

    protected Prepared() {}

    public Prepared(String sku, String name, Long price, Long calories) {
        super(sku, name, price);
        this.calories = calories;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Prepared.class.getSimpleName() + "[" , "]");
        sj.add(super.toString()).add(calories.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prepared)) return false;
        if (!super.equals(o)) return false;
        Prepared other = (Prepared) o;
        return Objects.equals(calories, other.calories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), calories);
    }
}
