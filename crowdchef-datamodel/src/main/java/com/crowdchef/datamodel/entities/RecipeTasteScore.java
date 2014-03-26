package com.crowdchef.datamodel.entities;

import javax.persistence.*;

@Entity
@Table(name = "recipe_taste_score")
public class RecipeTasteScore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_taste_score_id_seq")
    @SequenceGenerator(name = "recipe_taste_score_id_seq", sequenceName = "recipe_taste_score_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private Integer sweet;
    private Integer salty;
    private Integer sour;
    private Integer savory;
    private Integer spicy;

    public RecipeTasteScore(){

    }

    public RecipeTasteScore(Recipe recipe, Integer sweet, Integer sour, Integer salty, Integer spicy, Integer savory){
        this.recipe = recipe;
        this.sweet = sweet;
        this.sour = sour;
        this.salty = salty;
        this.spicy = spicy;
        this.savory = savory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSweet() {
        return sweet;
    }

    public void setSweet(Integer sweet) {
        this.sweet = sweet;
    }

    public Integer getSalty() {
        return salty;
    }

    public void setSalty(Integer salty) {
        this.salty = salty;
    }

    public Integer getSour() {
        return sour;
    }

    public void setSour(Integer sour) {
        this.sour = sour;
    }

    public Integer getSavory() {
        return savory;
    }

    public void setSavory(Integer savory) {
        this.savory = savory;
    }

    public Integer getSpicy() {
        return spicy;
    }

    public void setSpicy(Integer spicy) {
        this.spicy = spicy;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
