package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
public class Course {
    
    @Id   // assigned/natural key (note: no @GeneratedValue annotation)
    private String courseId;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PossibleCourseOffering> possibleCourseOfferings = new ArrayList<>();

    public Course() { }
    
    public Course(String courseId, Textbook textbook, Instructor instructor) {
	this.courseId = courseId;
        PossibleCourseOffering pco = new PossibleCourseOffering(this, textbook, instructor);
        this.possibleCourseOfferings.add(pco);
    }

    public String getCourseId() {
	return courseId;
    }
    public void setCourseId(String courseId) {
	this.courseId = courseId;
    }


    // business rule: any textbook can be used by any instructor
    public void addTextbook(Textbook textbook) {
        for (Instructor instructor : this.getInstructors()) {
            this.possibleCourseOfferings.add(new PossibleCourseOffering(this, textbook, instructor));
        }
    }
    
    // business rule: any instructor can teach with any textbook
    public void addInstructor(Instructor instructor) {
        for (Textbook textbook : this.getTextbooks()) {
            this.possibleCourseOfferings.add(new PossibleCourseOffering(this, textbook, instructor));
        }
    }
    // we would also need to implement removeTextbook() and removeInstructor()...

    public List<Textbook> getTextbooks() {
        return getPossibleCourseOfferings().stream()
            .map(PossibleCourseOffering::getTextbook).distinct()
            .collect(Collectors.toList());
    }
    public List<Instructor> getInstructors() {
        return getPossibleCourseOfferings().stream()
            .map(PossibleCourseOffering::getInstructor).distinct()
            .collect(Collectors.toList());
    }
    
    public List<PossibleCourseOffering> getPossibleCourseOfferings() {
	return this.possibleCourseOfferings;
    }

    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Course.class.getSimpleName() + "[" , "]");
	sj.add(courseId);
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Course)) return false;
	return courseId != null && courseId.equals(((Course) o).getCourseId());
    }

    @Override
    public int hashCode() {
	return this.courseId.hashCode();
    }
    
}
