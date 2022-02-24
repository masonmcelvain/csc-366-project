package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity
public class Hobby {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    // Person (arbitrarily) designated as "owning" class in the relationship
    @ManyToMany(mappedBy = "hobbies")
    private Set<Person> people = new HashSet<>();
    
    public Hobby() { }
    
    public Hobby(String name) {
	this.name = name;
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

    public void addPerson(Person p) {
	people.add(p);
        p.getHobbies().add(this);
    }
    public void removePerson(Person p) {
	people.remove(p);
        p.getHobbies().remove(this);
    }
    public Set<Person> getPeople() {
	return this.people;
    }
    
    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Hobby.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(name);
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Hobby)) return false;
	return id != null && id.equals(((Hobby) o).getId());
    }

    @Override
    public int hashCode() {
	return (int) (id ^ (id >>> 32));
    }
    
}
