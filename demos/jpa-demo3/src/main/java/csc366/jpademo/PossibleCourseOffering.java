package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"course_id", "textbook_id", "instructor_id"}))
public class PossibleCourseOffering {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "textbook_id", nullable = false)
    private Textbook textbook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;
    
    public PossibleCourseOffering() { }
    
    public PossibleCourseOffering(Course course, Textbook textbook, Instructor instructor) {
	this.course = course;
	this.textbook = textbook;
	this.instructor = instructor;
    }
    
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
    }

    public Course getCourse() {
	return course;
    }
    public void setCourse(Course course) {
	this.course = course;
    }

    public Textbook getTextbook() {
	return textbook;
    }
    public void setTextbook(Textbook textbook) {
	this.textbook = textbook;
    }

    public Instructor getInstructor() {
	return instructor;
    }
    public void setInstructor(Instructor instructor) {
	this.instructor = instructor;
    }
        
    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , PossibleCourseOffering.class.getSimpleName() + "\r\n\t[" , "]");
	sj.add(id.toString()).add(course.toString()).add(textbook.toString()).add(instructor.toString());
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof PossibleCourseOffering)) return false;
	return id != null && id.equals(((PossibleCourseOffering) o).getId());
    }

    @Override
    public int hashCode() {
	return (int) (id ^ (id >>> 32));
    }
    
}
