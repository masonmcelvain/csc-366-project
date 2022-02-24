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
public class Textbook {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull   // this, or nullable=false on @Column; @NotNull is separate from JPA, allows validation without DB call
    @Column(unique=true)
    private String title;

    @OneToMany(mappedBy = "textbook", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PossibleCourseOffering> possibleCourseOfferings = new ArrayList<>();

    public Textbook() { }
    
    public Textbook(String title) {
	this.title = title;
    }
    
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
    }
    
    public String getTitle() {
	return title;
    }
    public void setTitle(String title) {
	this.title = title;
    }

    public List<PossibleCourseOffering> getPossibleCourseOfferings() {
	return this.possibleCourseOfferings;
    }

    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Textbook.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(title);
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Textbook)) return false;
	return id != null && id.equals(((Textbook) o).getId());
    }

    @Override
    public int hashCode() {
	return (int) (id ^ (id >>> 32));
    }
    
}
