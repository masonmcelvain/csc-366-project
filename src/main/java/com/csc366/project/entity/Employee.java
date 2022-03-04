package com.csc366.project.entity;

import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

//import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "locId")
  private Location location;

  @OneToMany(mappedBy = "employee")
  private List<Paycheck> paychecks = new ArrayList<>();

  private String firstName;
  private String lastName;
  private LocalDate startDate;
  @Nullable
  private Long managedBy = null;
  @Nullable
  private LocalDate endDate;

  public Employee(String FirstName, String LastName, LocalDate StartDate) {
    this.firstName = FirstName;
    this.lastName = LastName;
    this.startDate = StartDate;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getStartDate(){
    return startDate;
  }

  public void setStartDate(LocalDate startDate){
    this.startDate = startDate;
  }

  public LocalDate getEndDate(){
    return endDate;
  }

  public void setEndDate(LocalDate endDate){
    this.endDate = endDate;
  }

  public Long getManagedBy(){
    return managedBy;
  }

  public void setManagedBy(Long managerId){
    this.managedBy = managerId;
  }

 public void addPaycheck(Paycheck p){
    paychecks.add(p);
    p.setEmployee(this);
  }

  public List<Paycheck> getPaychecks(){
    return this.paychecks;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Location getLocation(){
    return location;
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner("," , Employee.class.getSimpleName() + "[" , "]");
    sj.add(id.toString()).add(firstName).add(lastName).add(startDate.toString()).add("paychecks="+paychecks.toString());
    return sj.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Employee)) return false;
    Employee other = (Employee) o;
    return Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}






