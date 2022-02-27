package com.csc366.project.entity.key;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecipeKey implements Serializable {

    @Column(name = "sku")
    String sku;

    @Column(name = "ingredient_name")
    String ingredientName;

    protected RecipeKey() {}

    public RecipeKey(String sku, String ingredientName) {
        this.sku = sku;
        this.ingredientName = ingredientName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , RecipeKey.class.getSimpleName() + "[" , "]");
        sj.add(sku).add(ingredientName);
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeKey)) return false;
        RecipeKey other = (RecipeKey) o;
        return Objects.equals(sku, other.sku) && Objects.equals(ingredientName, other.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, ingredientName);
    }
}
