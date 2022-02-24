package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
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
public class Instructor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // @NotNull is an alternative to nullable=false on @Column
    // (@NotNull is separate from JPA, allows validation without DB call)
    @NotNull   
    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PossibleCourseOffering> possibleCourseOfferings = new ArrayList<>();

    public Instructor() { }
    
    public Instructor(String name) {
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

    public List<PossibleCourseOffering> getPossibleCourseOfferings() {
	return this.possibleCourseOfferings;
    }

    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Instructor.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(name);
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Instructor)) return false;
	return id != null && id.equals(((Instructor) o).getId());
    }

    @Override
    public int hashCode() {
	return (int) (id ^ (id >>> 32));
    }
    
}
