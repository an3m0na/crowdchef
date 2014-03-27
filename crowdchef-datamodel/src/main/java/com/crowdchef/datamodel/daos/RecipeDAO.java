package com.crowdchef.datamodel.daos;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.ValidationErrorCode;
import com.crowdchef.datamodel.ValidationException;
import com.crowdchef.datamodel.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeDAO {
    private CrowdChefDatabase database;

    public RecipeDAO(CrowdChefDatabase database) {
        this.database = database;
    }

    public Recipe updateRecipe(Long id, String name, String description, String directions, String tags, String imageUrl, User user) throws ValidationException {
        Recipe recipe;
        if (id == null) {
            recipe = new Recipe(name, user);
        } else {
            recipe = getRecipe(id);
        }
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setDirections(directions);
        recipe.setTags(tags);
        recipe.setImageUrl(imageUrl);
        database.saveOrUpdate(recipe, Recipe.class);
        return recipe;
    }

    public Recipe updateRecipe(Recipe recipe) throws ValidationException {
        database.saveOrUpdate(recipe, Recipe.class);
        return getRecipe(recipe.getId());
    }

    public Recipe updateRecipe(Long id, String name, String description, String directions, String tags, String imageUrl, Long userId) throws ValidationException {
        return updateRecipe(id, name, description, directions, tags, imageUrl, new UserDAO(database).getUser(userId));
    }

    public Recipe deleteIngredients(Recipe recipe) {
        //List<Ingredient> ingredients = recipe.getIngredients();
        //recipe.setIngredients(null);
        //database.delete(ingredients, Ingredient.class);
        recipe.getIngredients().clear();
        database.saveOrUpdate(recipe, Recipe.class);
        return recipe;
    }

    public Recipe deleteIngredients(Long id) {
        return deleteIngredients(getRecipe(id));
    }

    public Recipe addIngredient(Recipe recipe, Ingredient ingredient) {
        List<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients == null) {
            ingredients = new ArrayList<Ingredient>();
            recipe.setIngredients(ingredients);
        }
        ingredient.setRecipe(recipe);
        ingredients.add(ingredient);
        database.save(ingredient, Ingredient.class);
        return recipe;
    }

    public Recipe addIngredient(Long id, Ingredient ingredient) {
        Recipe recipe = getRecipe(id);
        addIngredient(recipe, ingredient);
        return recipe;
    }

    public Recipe addIngredients(Recipe recipe, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            recipe = addIngredient(recipe, ingredient);
        }
        return recipe;
    }

    public Recipe addIngredients(Long id, List<Ingredient> ingredients) {
        return addIngredients(getRecipe(id), ingredients);
    }

    public Recipe addIngredient(Recipe recipe, String name, String description, String quantity, Integer ord) {
        return addIngredient(recipe, new Ingredient(recipe, name, description, quantity, ord));
    }


    public void deleteRecipe(Long id) throws ValidationException {
        Recipe recipe = getRecipe(id);
        database.delete(recipe, Recipe.class);
    }

    public void deleteAllRecipes() throws ValidationException {
        List<Recipe> recipes = getRecipesByIds(null);
        database.delete(recipes, Recipe.class);
    }

    public Recipe getRecipe(Long id) throws ValidationException {
        List<Recipe> result = database.retrieve("OneRecipeById", "id", id, Recipe.class);
        if (result.size() < 1)
            throw new ValidationException(ValidationErrorCode.ID_NOT_EXIST);
        else if (result.size() > 1)
            throw new ValidationException(ValidationErrorCode.TOO_MANY_RESULTS);
        return result.get(0);
    }

    public List<Recipe> getAllRecipes() {
        return getRecipesByIds(null);
    }

    public List<Recipe> getRecipesByIds(List<Long> ids) {
        List<Recipe> result;
        if (ids == null) {
            result = database.retrieve("AllRecipes", Recipe.class);
            return result;
        }
        if(ids.size()<1)
            return new ArrayList<Recipe>();
        result = database.retrieve("AllRecipesInIds", "ids", ids, Recipe.class);
        return result;
    }

    public List<Recipe> getRecipesByName(String name) {
        List<Recipe> result = database.retrieve("AllRecipesLikeName", "name", name, Recipe.class);
        return result;
    }

    public void rateRecipe(Long recipeId, Long userId, Integer score) {
        HashMap map = new HashMap();
        map.put("recipe_id", recipeId);
        map.put("user_id", userId);
        List<RecipeRating> ratings = database.retrieve("OneRating", map, RecipeRating.class);
        RecipeRating rating;
        if (ratings == null || ratings.size() < 1) {
            rating = new RecipeRating(recipeId, score, userId);
        } else {
            rating = ratings.get(0);
            rating.setRating(score);
        }
        database.saveOrUpdate(rating, RecipeRating.class);
    }

    public Recipe assignTaste(Recipe recipe, RecipeTasteScore newTaste) {
        RecipeTasteScore oldTaste = recipe.getTasteScore();
        if (oldTaste == null) {
            newTaste.setRecipe(recipe);
            recipe.setTasteScore(newTaste);
            database.save(newTaste, RecipeTasteScore.class);
        } else {
            oldTaste.setSweet(newTaste.getSweet());
            oldTaste.setSalty(newTaste.getSalty());
            oldTaste.setSour(newTaste.getSour());
            oldTaste.setSpicy(newTaste.getSpicy());
            oldTaste.setSavory(newTaste.getSavory());
            database.saveOrUpdate(oldTaste, RecipeTasteScore.class);
        }
        return recipe;
    }

    public Recipe assignTaste(Long recipeId, RecipeTasteScore tasteScore) {
        return assignTaste(getRecipe(recipeId), tasteScore);
    }

    public Recipe assignTaste(Long recipeId, Integer sweet, Integer sour, Integer salty, Integer spicy, Integer savory) {
        return assignTaste(recipeId, new RecipeTasteScore(null, sweet, sour, salty, spicy, savory));
    }
}
