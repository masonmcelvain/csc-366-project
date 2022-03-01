package com.csc366.project.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="contract")
public class Contract {
    @Embeddable
    public static class ContractId implements Serializable {
        @Column(nullable=false, updatable = false)
        private String productId;

        @Column(nullable=false, updatable = false)
        private Long supplierId;

        @Column(nullable=false, updatable = false)
        private Long locationId;

        public ContractId() {}

        public ContractId(String productId, Long supplierId, Long locationId) {
            this.productId = productId;
            this.supplierId = supplierId;
            this.locationId = locationId;
        }

        public String getProductId() {
            return productId;
        }

        public Long getSupplierId() {
            return supplierId;
        }

        public Long getLocationId() {
            return locationId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ContractId)) return false;
            ContractId other = (ContractId) o;
            return Objects.equals(productId, other.productId) && Objects.equals(locationId, other.locationId);
        }

    }
    @EmbeddedId
    private ContractId contractId;
    
    private String type;
    private int rate; // in cents
    private int quantity;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId", insertable = false, updatable = false)
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
        this.contractId = new ContractId(product.getSku(), supplier.getId(), location.getId());
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

    public ContractId getId() {
        return contractId;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Contract.class.getSimpleName() + "[" , "]");
	    sj.add(contractId.toString()).add(supplier.toString()).add(product.toString()).add(location.toString()).add(date.toString()).add(Integer.toString(quantity)).add(Integer.toString(rate)).add(type);
	    return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	    if (this == o) 
            return true;
	    if (!(o instanceof Contract)) 
            return false;
	    return contractId != null && contractId.equals(((Contract) o).getId());
    }

    @Override
    public int hashCode() {
	    return Objects.hash(contractId);
    }
}
