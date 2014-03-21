package com.crowdchef.datamodel.entities;


import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "OneRecipeById",
                query = "SELECT COALESCE((SELECT avg(rating) FROM recipe_rating WHERE recipe_id = a.id), 0) rating, a.* FROM recipe a WHERE id = :id",
                resultClass = Recipe.class
        ),
        @NamedNativeQuery(
                name = "AllRecipesLikeName",
                query = "SELECT COALESCE((SELECT avg(rating) FROM recipe_rating WHERE recipe_id = a.id), 0) rating, a.* FROM recipe a WHERE LOWER(name) LIKE '%'||LOWER(:name)||'%'",
                resultClass = Recipe.class
        ),
        @NamedNativeQuery(
                name = "AllRecipes",
                query = "SELECT COALESCE((SELECT avg(rating) FROM recipe_rating WHERE recipe_id = a.id), 0) rating, a.* FROM recipe a",
                resultClass = Recipe.class
        ),
        @NamedNativeQuery(
                name = "AllRecipesInIds",
                query = "SELECT COALESCE((SELECT avg(rating) FROM recipe_rating WHERE recipe_id = a.id), 0) rating, a.* FROM recipe a WHERE id in (:ids)",
                resultClass = Recipe.class
        )

})
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_id_seq")
    @SequenceGenerator(name = "recipe_id_seq", sequenceName = "recipe_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private String tags;

    private byte[] image;

    @Column(name = "image_url")
    private String imageUrl;

    private String directions;

    @Column(name = "create_time", columnDefinition = "datetime", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User createUser;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL ,orphanRemoval=true)
    @OrderBy("ord asc")
    private List<Ingredient> ingredients;

    @Transient
    private BigDecimal rating;

    public Recipe() {
    }

    public Recipe(String name, User createUser) {
        this.name = name;
        this.createUser = createUser;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Recipe{" + getId() + " added " + getCreateTime() + " by " + (getCreateUser() != null ? getCreateUser().getUsername() : null) + "} = " + getName() + " (" + getDescription() + "): " + getDirections() + "\n\tIngredients: " + (getIngredients() != null ? getIngredients().toString() : null);
    }
}