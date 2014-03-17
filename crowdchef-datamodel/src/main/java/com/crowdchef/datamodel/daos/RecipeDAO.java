package com.crowdchef.datamodel.daos;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.ValidationErrorCode;
import com.crowdchef.datamodel.ValidationException;
import com.crowdchef.datamodel.entities.Ingredient;
import com.crowdchef.datamodel.entities.Recipe;
import com.crowdchef.datamodel.entities.User;

import java.util.ArrayList;
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

    public Recipe updateRecipe(Long id, String name, String description, String directions, String tags, String imageUrl, Long userId) throws ValidationException {
        return updateRecipe(id, name, description, directions, tags, imageUrl, new UserDAO(database).getUser(userId));
    }

    public Recipe deleteIngredients(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        recipe.setIngredients(null);
        database.delete(ingredients, Ingredient.class);
        return recipe;
    }

    public Recipe deleteIngredients(Long id) {
        return deleteIngredients(getRecipe(id));
    }

    public Recipe addIngredient(Recipe recipe, Ingredient ingredient) {
        List<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients == null) {
            ingredients = new ArrayList<Ingredient>();
        }
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);
        database.saveOrUpdate(ingredient, Ingredient.class);
        return recipe;
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
        List<Recipe> result = database.retrieve("OneRecipeById", "id", "" + id, Recipe.class);
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
        String idList = ids.toString();
        result = database.retrieve("AllRecipesInIds", "ids", idList.substring(1, idList.length() - 1), Recipe.class);
        return result;
    }

    public List<Recipe> getRecipesByName(String name) {
        List<Recipe> result = database.retrieve("AllRecipesLikeName", "name", name, Recipe.class);
        return result;
    }
}
