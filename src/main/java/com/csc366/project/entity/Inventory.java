package com.csc366.project.entity;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.*;

@Entity
@Table(name = "inventory"/*,
  uniqueConstraints = @UniqueConstraint(columnNames={"LocationId", "sku"})*/)
public class Inventory {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private int quantity;

  @ManyToOne
  @JoinColumn(name = "locId")
  private Location location;

  @ManyToOne
  @JoinColumn(name = "sku")
  private Product product;

  public Inventory(Location locationId, Product product, int quantity){
    this.location = locationId;
    this.product = product;
    this.quantity = quantity;
  }

  public Long getId(){
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Location getLocationId() {
    return location;
  }

  public void setLocationId(Location location) {
    this.location = location;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner("," , Inventory.class.getSimpleName() + "[" , "]");
    sj.add(id.toString()).add(location.toString()).add(product.toString()).add(Integer.toString(quantity));
    return sj.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Inventory)) return false;
    Inventory other = (Inventory) o;
    return Objects.equals(location, other.location) && Objects.equals(product, other.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
