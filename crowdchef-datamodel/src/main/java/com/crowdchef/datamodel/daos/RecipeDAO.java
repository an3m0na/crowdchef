package com.crowdchef.datamodel.daos;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.ValidationErrorCode;
import com.crowdchef.datamodel.ValidationException;
import com.crowdchef.datamodel.entities.Recipe;

import java.util.List;

public class RecipeDAO {
    private CrowdChefDatabase database;

    public RecipeDAO(CrowdChefDatabase database) {
        this.database = database;
    }

    public Long updateRecipe(Long id, String name, String description, String directions, String tags, String imageUrl, Long userId) throws ValidationException {
        Recipe recipe;
        if (id == null) {
            recipe = new Recipe(name, new UserDAO(database).getUser(userId));
        } else {
            recipe = getRecipe(id);
        }
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setDirections(directions);
        recipe.setTags(tags);
        recipe.setImageUrl(imageUrl);
        database.saveOrUpdate(recipe, Recipe.class);
        return recipe.getId();
    }

    public Recipe getRecipe(Long id) throws ValidationException {
        List<Recipe> result = database.retrieve("OneRecipeById", "id", "" + id, Recipe.class);
        if (result.size() < 1)
            throw new ValidationException(ValidationErrorCode.ID_NOT_EXIST);
        else if (result.size() > 1)
            throw new ValidationException(ValidationErrorCode.TOO_MANY_RESULTS);
        return result.get(0);
    }

    public List<Recipe> getRecipesByName(String name) {
        List<Recipe> result = database.retrieve("AllRecipesLikeName", "name", name, Recipe.class);
        return result;
    }
}
