package com.csc366.project.entity;

import java.util.Objects;
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
import javax.persistence.CascadeType;

//import javax.validation.constraints.NotNull;

@Entity
@Table(name = "paycheck",
  uniqueConstraints = @UniqueConstraint(columnNames={"EmpId", "Date"}))
public class Paycheck {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "empId", insertable = true, updatable = false)
  private Employee employee;

  private String Date;
  private double Rate;
  private double Hours;
  private double Taxes;
  private double SickHoursAccrued;
  private double OvertimeHours;
  private double NetPay;
  private double SickHoursUsed;

  public Paycheck() { }

  public Paycheck(Employee employee, String Date, double Rate, double Hours, double Taxes,
                  double SickHoursAccrued, double OvertimeHours, double NetPay, double SickHoursUsed) {
    this.employee = employee;
    this.Date = Date;
    this.Rate = Rate;
    this.Hours = Hours;
    this.Taxes = Taxes;
    this.SickHoursAccrued = SickHoursAccrued;
    this.OvertimeHours = OvertimeHours;
    this.NetPay = NetPay;
    this.SickHoursUsed = SickHoursUsed;
  }

  public Long getId(){
    return id;
  }
  public void setId(Long id){
    this.id = id;
  }

  public String getDate() {
    return Date;
  }
  public void setDate(String Date) {
    this.Date = Date;
  }

  public double getRate() {
    return Rate;
  }
  public void setRate(double Rate) {
    this.Rate = Rate;
  }

  public double getHours() {
    return Hours;
  }
  public void setHours(double Hours) {
    this.Hours = Hours;
  }

  public double getTaxes() {
    return Taxes;
  }
  public void setTaxes(double Taxes) {
    this.Taxes = Taxes;
  }

  public double getSickHoursAccrued() {
    return SickHoursAccrued;
  }
  public void setSickHoursAccrued(double SickHoursAccrued) {
    this.SickHoursAccrued = SickHoursAccrued;
  }

  public double getOverTimeHours() {
    return OvertimeHours;
  }
  public void setOvertimeHours(double OvertimeHours) {
    this.OvertimeHours = OvertimeHours;
  }

  public double getNetPay() {
    return NetPay;
  }
  public void setNetPay(double NetPay) {
    this.NetPay = NetPay;
  }

  public double getSickHoursUsed() {
    return SickHoursUsed;
  }
  public void setSickHoursUsed(double SickHoursUsed) {
    this.SickHoursUsed = SickHoursUsed;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee(){
    return employee;
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner("," , Paycheck.class.getSimpleName() + "[" , "]");
    sj.add(employee.toString()).add(Date).add(Double.toString(Rate)).add(Double.toString(Hours)).add(Double.toString(Taxes)).add(Double.toString(SickHoursAccrued))
      .add(Double.toString(OvertimeHours)).add(Double.toString(NetPay)).add(Double.toString(SickHoursUsed));
    return sj.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Paycheck)) return false;
    Paycheck other = (Paycheck) o;
    return Objects.equals(employee, other.employee) && Objects.equals(Date, other.Date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}




