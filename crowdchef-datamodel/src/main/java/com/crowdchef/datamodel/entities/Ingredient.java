package com.crowdchef.datamodel.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ingredient_id_seq")
    @SequenceGenerator(name="ingredient_id_seq", sequenceName="ingredient_id_seq", allocationSize=1)
    private Long id;

    private String name;

    private String description;

    private String quantity;

    private Integer ord;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Ingredient() {

    }

    public Ingredient(Recipe recipe, String name) {
        this.name = name;
        this.recipe = recipe;
    }

    public Ingredient(Recipe recipe, String name, String description, String quantity, Integer ord) {
        this.name = name;
        this.recipe = recipe;
        this.description = description;
        this.quantity = quantity;
        this.ord = ord;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        return "Ingredient{" + getId() + "} = " + getName() + " (" + getDescription() + "): " + getQuantity();
    }
}
