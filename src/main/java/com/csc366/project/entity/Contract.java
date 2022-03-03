package com.csc366.project.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="contract")
public class Contract {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String type;
    private int rate; // in cents
    private int quantity;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = true, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId", insertable = true, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId", insertable = true, updatable = false)
    private Location location;

    public Contract() {}

    public Contract(String type, int rate, int quantity, LocalDate date, Product product, Supplier supplier, Location location) {
        this.type = type;
        this.rate = rate;
        this.quantity = quantity;
        this.date = date;
        this.product = product;
        this.supplier = supplier;
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Location getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Contract.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(supplier.toString()).add(product.toString()).add(location.toString()).add(date.toString()).add(Integer.toString(quantity)).add(Integer.toString(rate)).add(type);
	    return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	    if (this == o) 
            return true;
	    if (!(o instanceof Contract)) 
            return false;
	    return id != null && id.equals(((Contract) o).getId());
    }

    @Override
    public int hashCode() {
	    return Objects.hash(id);
    }
}
