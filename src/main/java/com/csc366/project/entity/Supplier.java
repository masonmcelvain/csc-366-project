package com.csc366.project.entity;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String emailAddress;
    
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;


    public Supplier() {}

    public Supplier(String name, String emailAddress, String phoneNumber, Address address) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
	    return id;
    }
    public void setId(Long id) {
	    this.id = id;
    }
    
    public String getName() {
	    return name;
    }
    public void setName(String name) {
	    this.name = name;
    }

    public String getEmailAddress() {
	    return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
    }
    
    public String getPhoneNumber() {
	    return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
	    return address;
    }
    
    public void setAddress(Address address) {
	    this.address = address;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Supplier.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(name).add(emailAddress).add(phoneNumber);
	    return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	    if (this == o) 
            return true;
	    if (!(o instanceof Supplier)) 
            return false;
	    return id != null && id.equals(((Supplier) o).getId());
    }

    @Override
    public int hashCode() {
	    return Objects.hash(id);
    }
}
