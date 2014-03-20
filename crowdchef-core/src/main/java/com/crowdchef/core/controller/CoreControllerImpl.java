package com.crowdchef.core.controller;


import com.crowdchef.core.handlers.RecipeHandler;
import com.crowdchef.core.handlers.UserHandler;
import com.crowdchef.core.retriever.Indexer;
import com.crowdchef.core.retriever.Searcher;
import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.ValidationErrorCode;
import com.crowdchef.datamodel.ValidationException;
import com.crowdchef.datamodel.entities.Ingredient;
import com.crowdchef.datamodel.entities.Recipe;
import com.crowdchef.datamodel.entities.User;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CoreControllerImpl implements CoreController {
    private CrowdChefDatabase database;
    private UserHandler userHandler;
    private RecipeHandler recipeHandler;
    private final Searcher searcher;
    private final Indexer indexer;

    protected CoreControllerImpl() {
        this.database = new CrowdChefDatabase();
        this.userHandler = new UserHandler(this.database);
        this.recipeHandler = new RecipeHandler(this.database);
        this.searcher = new Searcher();
        this.indexer = new Indexer();
    }

    public static Gson buildGson(String excludeField) {
        return buildGson(Arrays.asList(excludeField));
    }

    public static Gson buildGson(final List<String> excludeFields) {
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {

                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }

                    public boolean shouldSkipField(FieldAttributes f) {
                        return excludeFields.contains(f.getName());
                    }
                })
                .create();
    }

    public static JsonElement toShortForm(List<Recipe> recipeList) {
        JsonArray result = new JsonArray();
        JsonObject tmp;
        for (Recipe r : recipeList) {
            tmp = new JsonObject();
            tmp.addProperty("id", r.getId());
            tmp.addProperty("name", r.getName());
            result.add(tmp);
        }
        return result;
    }

    @Override
    public JsonElement getRecipe(Long id) {
        Recipe recipe = recipeHandler.getRecipe(id);
        JsonElement result = buildGson(Arrays.asList("recipe", "createUser")).toJsonTree(recipe);
        result.getAsJsonObject().addProperty("createUser", recipe.getCreateUser().getUsername());
        return result;
    }

    @Override
    public JsonElement listRecipes() {
        List<Recipe> recipes = recipeHandler.getRecipes();
        JsonElement result = buildGson(Arrays.asList("recipe", "createUser")).toJsonTree(recipes);
        return result;
    }

    @Override
    public JsonElement listRecipes(boolean shortForm) {
        if (!shortForm)
            return listRecipes();
        return toShortForm(recipeHandler.getRecipes());
    }

    @Override
    public JsonElement listRecipes(List<Long> ids) {
        List<Recipe> recipes = recipeHandler.getRecipesByIds(ids);
        return buildGson(Arrays.asList("recipe", "createUser")).toJsonTree(recipes);
    }

    @Override
    public JsonElement listRecipes(List<Long> ids, boolean shortForm) {
        if (!shortForm)
            return listRecipes(ids);
        return toShortForm(recipeHandler.getRecipesByIds(ids));
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeHandler.deleteRecipe(id);
    }

    protected List<Ingredient> parseIngredients(JsonElement recipeJson) {
        JsonElement ingredients = recipeJson.getAsJsonObject().get("ingredients");
        List<Ingredient> ret = new ArrayList<Ingredient>();
        if (ingredients == null)
            return ret;
        for (JsonElement i : ingredients.getAsJsonArray()) {
            Ingredient ingredient = new Gson().fromJson(i.getAsJsonObject(), Ingredient.class);
            ret.add(ingredient);
        }
        return ret;
    }

    @Override
    public Long addRecipe(JsonElement recipeJson) {
        Recipe recipe = buildGson(Arrays.asList("id", "userId", "ingredients")).fromJson(recipeJson, Recipe.class);
        JsonElement userId = recipeJson.getAsJsonObject().get("userId");
        if (userId == null)
            throw new ValidationException(ValidationErrorCode.USER_NOT_PASSED);
        recipe.setCreateUser(userHandler.getUser(userId.getAsBigInteger().longValue()));
        recipe = recipeHandler.addRecipe(recipe);
        recipeHandler.addIngredients(recipe, parseIngredients(recipeJson));
        return recipe.getId();
    }

    @Override
    public void updateRecipe(JsonElement recipeJson) {
        Recipe recipe = buildGson(Arrays.asList("userId", "ingredients")).fromJson(recipeJson, Recipe.class);
        recipe = recipeHandler.updateRecipe(recipe);
        recipe = recipeHandler.deleteIngredients(recipe);
        recipeHandler.addIngredients(recipe, parseIngredients(recipeJson));
    }

    @Override
    public void updateIngredients(JsonElement recipeJson) {
        JsonElement recipeId = recipeJson.getAsJsonObject().get("recipeId");
        if (recipeId == null) {
            throw new ValidationException(ValidationErrorCode.ID_NOT_EXIST);
        }
        Long idLong = recipeId.getAsBigInteger().longValue();
        recipeHandler.deleteIngredients(idLong);
        recipeHandler.addIngredients(idLong, parseIngredients(recipeJson));
    }

    @Override
    public JsonElement getUserInfo(Long id) {
        return buildGson("user").toJsonTree(userHandler.getUserInfo(id));
    }

    @Override
    public Long registerUser(String username, String password) {
        return userHandler.registerUser(username, password).getId();
    }

    @Override
    public Long checkUser(String username, String password) {
        User user = userHandler.checkUser(username, password);
        return user == null ? -1 : user.getId();
    }

    @Override
    public void unregisterUser(Long id) {
        userHandler.deleteUser(id);
    }

    @Override
    public JsonElement searchRecipes(String searchQuery, String field) {
        List<Long> recipeIds = searcher.search(searchQuery, field);
        if(recipeIds == null)
            indexRecipes();
        searcher.search(searchQuery, field);
        return listRecipes(recipeIds);
    }

    @Override
    public void indexRecipes() {
        indexer.index(recipeHandler.getRecipes());
    }
}
