package com.csc366.project.entity.key;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LineItemKey implements Serializable {

    @Column(name = "sku")
    String sku;

    @Column(name = "orderId")
    Long orderId;

    public LineItemKey() {}

    public LineItemKey(String sku, Long orderId) {
        this.sku = sku;
        this.orderId = orderId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , LineItemKey.class.getSimpleName() + "[" , "]");
        sj.add(sku).add(orderId.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineItemKey)) return false;
        LineItemKey other = (LineItemKey) o;
        return Objects.equals(sku, other.getSku()) && Objects.equals(orderId, other.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, orderId.toString());
    }
}