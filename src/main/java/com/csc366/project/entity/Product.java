package com.csc366.project.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

/**
 * Price is in cents to avoid floating point arithmetic.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product")
public class Product {
    @Id
    private String sku;
    private String name;
    private Long price;
    @OneToMany(mappedBy = "product")
    private Set<Recipe> recipes = new HashSet<Recipe>();

     @OneToMany(
        mappedBy="product",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<LineItem> lineItems = new ArrayList<>();

    protected Product() {}

    public Product(String sku, String name, Long price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addLineItem(LineItem l){
        lineItems.add(l);
        l.setProduct(this);
    }

    public void deleteLineItem(LineItem l){
        lineItems.remove(l);
        l.setProduct(null);
    }

    public List<LineItem> getLineItems() {
        return this.lineItems;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Product.class.getSimpleName() + "[" , "]");
        sj.add(sku).add(name).add(price.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        return Objects.equals(sku, other.sku) && Objects.equals(name, other.name) && Objects.equals(price, other.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, price);
    }
}
