package com.crowdchef.core.handlers;


import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.daos.RecipeDAO;
import com.crowdchef.datamodel.entities.Recipe;

import java.util.List;

public class RecipeHandler {
    private RecipeDAO recipeDao;

    public RecipeHandler(CrowdChefDatabase database) {
        this.recipeDao = new RecipeDAO(database);

    }

    public Recipe addRecipe(String name, String description, String directions, String tags, String imageUrl, Long userId) {
        return recipeDao.updateRecipe(null, name, description, directions, tags, imageUrl, userId);
    }

    public Recipe updateRecipe(Long id, String name, String description, String directions, String tags, String imageUrl, Long userId) {
        return recipeDao.updateRecipe(id, name, description, directions, tags, imageUrl, userId);
    }

    public Recipe getRecipe(Long id) {
        return recipeDao.getRecipe(id);
    }

    public List<Recipe> getRecipes() {
        return recipeDao.getAllRecipes();
    }

    public List<Recipe> getRecipesByName(String name) {
        return recipeDao.getRecipesByName(name);
    }

    public List<Recipe> getRecipesByIds(List<Long> ids) {
        return recipeDao.getRecipesByIds(ids);
    }

    public void deleteRecipe(Long id){
        recipeDao.deleteRecipe(id);
    }
}
