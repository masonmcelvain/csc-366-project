package com.csc366.project.entity;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "packaged")
public class Packaged extends Product {
    @Column(columnDefinition = "enum('XS','S','M','L','XL','XXL')")
    @Enumerated(EnumType.STRING)
    private Size size;

    protected Packaged() {}

    public Packaged(String sku, String name, Long price, Size size) {
        super(sku, name, price);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Packaged.class.getSimpleName() + "[" , "]");
        sj.add(super.toString()).add(size.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Packaged)) return false;
        if (!super.equals(o)) return false;
        Packaged other = (Packaged) o;
        return size == null ? other.getSize() == null : size.equals((other.getSize()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size);
    }
}
