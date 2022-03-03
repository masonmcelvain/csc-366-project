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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate openDate;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", insertable = true, updatable = false)
    private Owner owner;

    // public Location() {}

    public Location(LocalDate openDate, Address address, Owner owner) {
        this.openDate = openDate;
        this.address = address;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public Address getAddress() {
        return address;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Product.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(openDate.toString()).add(address.toString()).add(owner.toString()); //need address?
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	    if (this == o) 
            return true;
	    if (!(o instanceof Location)) 
            return false;
	    return id != null && id.equals(((Location) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


// package com.csc366.project.entity;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

// @Entity
// @Table(name="location")
// public class Location {
//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Long id;

//     public Location() { }

//     public Long getId() {
//         return id;
//     }
// }
