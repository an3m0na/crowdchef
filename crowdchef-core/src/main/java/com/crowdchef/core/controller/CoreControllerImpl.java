package com.crowdchef.core.controller;


import com.crowdchef.core.handlers.RecipeHandler;
import com.crowdchef.core.handlers.UserHandler;
import com.crowdchef.core.retriever.Indexer;
import com.crowdchef.core.retriever.Searcher;
import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.entities.Recipe;
import com.crowdchef.datamodel.entities.User;
import com.google.gson.*;

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
    public JsonElement listRecipes(List<Long> ids) {
        List<Recipe> recipes = recipeHandler.getRecipesByIds(ids);
        return buildGson(Arrays.asList("recipe", "createUser")).toJsonTree(recipes);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeHandler.deleteRecipe(id);
    }

    @Override
    public Long addRecipe(JsonElement recipeJson) {
        JsonObject recipeObject = recipeJson.getAsJsonObject();
        Recipe recipe = recipeHandler.addRecipe(recipeObject.get("name").getAsString(), recipeObject.get("description").getAsString(), recipeObject.get("directions").getAsString(), recipeObject.get("tags").getAsString(), recipeObject.get("imageUrl").getAsString(), recipeObject.get("userId").getAsBigInteger().longValue());
        return recipe.getId();
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
        return listRecipes(recipeIds);
    }

    @Override
    public void indexRecipes() {
        indexer.index(recipeHandler.getRecipes());
    }
}
