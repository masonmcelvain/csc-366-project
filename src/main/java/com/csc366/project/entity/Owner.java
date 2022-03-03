package com.csc366.project.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
@Table(name="owner")
public class Owner {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    // TODO: figure out whether we want to keep what we have right now for name
    private String firstName;
    private String lastName;

    // TODO: fix mappedBy = "location"
    @OneToMany(mappedBy = "owner")
    private Set<Location> locations = new HashSet<Location>();

    public Owner() {}

    public Owner(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.name = name;
    }
    // public String getFirstName() {
    //     return firstName;
    // }

    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }

    // public String getLastName() {
    //     return lastName;
    // }

    // public void setLastName(String lastName) {
    //     this.lastName = lastName;
    // }

    // public Set<Location> getLocations() {
    //     return locations;
    // }

    // public void setLocations(Set<Location> locations) {
    //     this.locations = locations;
    // }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Product.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(name);
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	    if (this == o) 
            return true;
	    if (!(o instanceof Owner)) 
            return false;
	    return id != null && id.equals(((Owner) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
