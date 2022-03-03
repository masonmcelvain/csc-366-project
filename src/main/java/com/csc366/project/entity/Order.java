package com.csc366.project.entity;

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

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = true)
    private Location location;

    @Column(name = "tax")
    private double tax;

    @Column(name = "total")
    private double total;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;

    /*
    @OneToMany(
        mappedBy="lineItem",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<LineItem> lineItems = new ArrayList<>();*/

    public Order() {}

    public Order(Location location, double tax, double total, Date date){
        this.location = location;
        this.tax = tax;
        this.total = total;
        this.date = date;
    }

    public Long getOrderId(){
        return this.orderId;
    }

    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
    public Location getLocation(){
        return this.location;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public double getTax(){
        return this.tax;
    }

    public void setTax(double tax){
        this.tax = tax;
    }

    public double getTotal(){
        return this.total;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date; 
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public void setCustomer(Customer customer){
        this.customer=customer;
    }

    /*
    public List<LineItem> getLineItems(){
        return this.lineItems;
    }

    public void deleteLineItem(LineItem l){
        lineItems.remove(l);
        l.setOrder(null);
    }

    public void addLineItem(LineItem l){
        lineItems.add(l);
        l.setOrder(this);
    }
*/
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Order.class.getSimpleName() + "[" , "]");
        sj.add(orderId.toString()).add(customer.getCustomerId().toString()).add(date.toString() + tax + total) ;
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order other = (Order) o;
        return Objects.equals(orderId, other.orderId);
    }

     @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

}