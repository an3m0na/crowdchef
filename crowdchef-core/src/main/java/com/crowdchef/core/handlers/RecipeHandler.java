package com.crowdchef.core.handlers;


import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.daos.RecipeDAO;
import com.crowdchef.datamodel.entities.Ingredient;
import com.crowdchef.datamodel.entities.Recipe;
import com.crowdchef.datamodel.entities.RecipeTasteScore;

import java.util.ArrayList;
import java.util.List;

public class RecipeHandler {
    private RecipeDAO recipeDao;

    public RecipeHandler(CrowdChefDatabase database) {
        this.recipeDao = new RecipeDAO(database);

    }

    public Recipe addRecipe(Recipe recipe) {
        return recipeDao.updateRecipe(recipe);
    }

    public Recipe addRecipe(String name, String description, String directions, String tags, String imageUrl, Long userId) {
        return recipeDao.updateRecipe(null, name, description, directions, tags, imageUrl, userId);
    }

    public Recipe updateRecipe(Long id, String name, String description, String directions, String tags, String imageUrl, Long userId) {
        return recipeDao.updateRecipe(id, name, description, directions, tags, imageUrl, userId);
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeDao.updateRecipe(recipe);
    }

    public Recipe addIngredient(Recipe recipe, Ingredient ingredient) {
        return recipeDao.addIngredient(recipe, ingredient);
    }

    public Recipe addIngredients(Long id, List<Ingredient> ingredients) {
        return recipeDao.addIngredients(id, ingredients);
    }

    public Recipe addIngredients(Recipe recipe, List<Ingredient> ingredients) {
        return recipeDao.addIngredients(recipe, ingredients);
    }

    public Recipe deleteIngredients(Long id) {
        return recipeDao.deleteIngredients(id);
    }

    public Recipe deleteIngredients(Recipe recipe) {
        return recipeDao.deleteIngredients(recipe);
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

    public void deleteRecipe(Long id) {
        recipeDao.deleteRecipe(id);
    }

    public void rateRecipe(Long recipeId, Long userId, Integer rating){
        recipeDao.rateRecipe(recipeId, userId, rating);
    }

    public void assignTaste(Long recipeId, RecipeTasteScore tasteScore){
        recipeDao.assignTaste(recipeId, tasteScore);
    }
}
