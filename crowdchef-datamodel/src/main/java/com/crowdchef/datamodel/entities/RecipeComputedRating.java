package com.crowdchef.datamodel.entities;


import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "recipe_rating_view")
@Immutable

public class RecipeComputedRating {

    @Id
    @Column(name = "recipe_id")
    private Long ratingId;

    @Column(name = "rating")
    private BigDecimal value;

    private Long votes;

    public RecipeComputedRating() {
    }

    public BigDecimal getValue() {
        return new BigDecimal(Math.floor(value.doubleValue() * 1e2) / 1e2);
    }

    public void setValue(BigDecimal rating) {
        this.value = rating;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }
}