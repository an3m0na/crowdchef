package com.crowdchef.core.handlers;


import com.crowdchef.core.DatabaseController;
import com.crowdchef.datamodel.daos.RecipeDAO;
import com.crowdchef.datamodel.entities.Recipe;

import java.util.List;

public class RecipeHandler {
    private RecipeDAO recipeDao;

    public RecipeHandler(DatabaseController databaseController) {
        this.recipeDao = new RecipeDAO(databaseController.getDatabase());

    }

    public Long addRecipe(String name, String description, String directions, String tags, String imageUrl, Long userId) {
        return recipeDao.updateRecipe(null, name, description, directions, tags, imageUrl, userId);
    }

    public Long updateRecipe(Long id, String name, String description, String directions, String tags, String imageUrl, Long userId) {
        return recipeDao.updateRecipe(id, name, description, directions, tags, imageUrl, userId);
    }

    public Recipe getRecipe(Long id) {
        return recipeDao.getRecipe(id);
    }

    public List<Recipe> getRecipesMatching(String name) {
        return recipeDao.getRecipesMatching(name);
    }
}
