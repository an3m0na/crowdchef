package com.crowdchef.datamodel.entities;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "OneRating",
                query = "SELECT * FROM recipe_rating WHERE recipe_id = :recipe_id AND user_id = :user_id",
                resultClass = RecipeRating.class
        )

})

@Entity
@Table(name = "recipe_rating")
public class RecipeRating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_rating_id_seq")
    @SequenceGenerator(name = "recipe_rating_id_seq", sequenceName = "recipe_rating_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "recipe_id")
    private Long recipeId;

    private Integer rating;

    @Column(name = "user_id")
    private Long userId;

    public RecipeRating() {

    }

    public RecipeRating(Long recipeId, Integer rating, Long userId) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.rating = rating;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
