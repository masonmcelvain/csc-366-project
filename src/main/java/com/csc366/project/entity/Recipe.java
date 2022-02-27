package com.csc366.project.entity;

import com.csc366.project.entity.key.RecipeKey;

import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe {

    @EmbeddedId
    private RecipeKey key;

    @ManyToOne
    @MapsId("sku")
    @JoinColumn(name = "sku")
    private Product product;

    @ManyToOne
    @MapsId("ingredientName")
    @JoinColumn(name = "ingredient_name")
    private Ingredient ingredient;

    protected Recipe() {}

    public Recipe(RecipeKey key, Product product, Ingredient ingredient) {
        this.key = key;
        this.product = product;
        this.ingredient = ingredient;
    }

    public RecipeKey getKey() {
        return key;
    }

    public void setKey(RecipeKey key) {
        this.key = key;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        key.setSku(product.getSku());
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        key.setIngredientName(ingredient.getName());
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Recipe.class.getSimpleName() + "[" , "]");
        sj.add(product.toString()).add(ingredient.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe other = (Recipe) o;
        return Objects.equals(key, other.key) && Objects.equals(product, other.product) && Objects.equals(ingredient, other.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key.hashCode(), product.hashCode(), ingredient.hashCode());
    }
}
