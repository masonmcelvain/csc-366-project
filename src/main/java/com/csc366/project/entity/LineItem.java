package com.csc366.project.entity;

import com.csc366.project.entity.key.LineItemKey;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.EmbeddedId;

@Entity
@Table(name = "lineItem")
public class LineItem{
    @EmbeddedId
    private LineItemKey key;

    @ManyToOne
    @MapsId("sku")
    @JoinColumn(name = "sku")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "tax")
    private double tax;

    public LineItem(){}

    public LineItem(LineItemKey key, Product product, Order order, int quantity, double subtotal, double tax){
        this.key = key;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.tax = tax;
    }

    public LineItemKey getLineItemKey(){
        return this.key;
    }

    public void setLineItemKey(LineItemKey key){
        this.key = key;
    }

    public Product getProduct(){
        return this.product;
    }

    public void setProduct(Product product){
        this.product = product;
        key.setSku(product.getSku());
    }

    public Order getOrder(){
        return this.order;
    }

    public void setOrder(Order order){
        this.order = order;
        key.setOrderId(order.getOrderId());
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getSubtotal(){
        return this.subtotal;
    }

    public void setSubtotal(double subtotal){
        this.subtotal = subtotal;
    }

    public double getTax(){
        return this.tax;
    }

    public void setTax(double tax){
        this.tax = tax;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , LineItem.class.getSimpleName() + "[" , "]");
        sj.add(order.getOrderId().toString() + quantity + subtotal + tax);
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineItem)) return false;
        LineItem other = (LineItem) o;
        return Objects.equals(order.getOrderId(), other.order.getOrderId()) && Objects.equals(product.getSku(), other.product.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getSku(), order.getOrderId());
    }

}