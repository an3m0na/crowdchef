package com.crowdchef.datamodel.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "OneRecipeById",
                query = "SELECT * FROM recipe WHERE idRecipe = :id",
                resultClass = Recipe.class
        ),
        @NamedNativeQuery(
                name = "AllRecipesLikeName",
                query = "SELECT * FROM recipe WHERE LOWER(name) LIKE '%'||LOWER(:name)||'%'",
                resultClass = Recipe.class
        ),
        @NamedNativeQuery(
                name = "AllRecipes",
                query = "SELECT * FROM recipe",
                resultClass = Recipe.class
        )
})
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String tags;

    @Column(columnDefinition = "blob")
    private byte[] image;

    @Column(name = "image_url")
    private String imageUrl;

    private String directions;

    @Column(name = "create_time", columnDefinition = "datetime", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createUser;


    private Recipe() {
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


    @Override
    public String toString() {
        return "Recipe{" + getId() + " added " + getCreateTime() + " by "+ getCreateUser().getUsername() + "} = " + getName() + " (" + getDescription() + "): " + getDirections();
    }
}