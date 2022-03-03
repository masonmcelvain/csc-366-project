package com.csc366.project.entity;

import java.util.StringJoiner;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity  
@Table(name="address", uniqueConstraints = @UniqueConstraint(columnNames={"street", "city", "state", "zip_code"}))
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street; 

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    
    public Address() { }
    
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    public Long getId() {
	    return id;
    }
    public void setId(Long id) {
	    this.id = id;
    }
    
    public String getStreet() {
	    return street;
    }
    public void setStreet(String street) {
	    this.street = street;
    }

    public String getCity() {
	    return city;
    }
    public void setCity(String city) {
	    this.city = city;
    }

    public String getState() {
	    return state;
    }
    public void setState(String state) {
	    this.state = state;
    }

    public String getZipCode() {
	    return zipCode;
    }
    public void setZipCode(String zipCode) {
	    this.zipCode = zipCode;
    }
        
    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Address.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(street).add(city).add(state).add(zipCode);
	    return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	    if (this == o) 
            return true;
	    if (!(o instanceof Address)) 
            return false;
	    return id != null && id.equals(((Address) o).getId());
    }

    @Override
    public int hashCode() {
	    return Objects.hash(id);
    }
    
}

