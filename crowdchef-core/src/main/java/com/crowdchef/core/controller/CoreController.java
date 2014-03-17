package com.crowdchef.core.controller;


import com.google.gson.JsonElement;

import java.util.List;

public interface CoreController {


    public JsonElement getRecipe(Long id);

    public JsonElement listRecipes();

    public JsonElement listRecipes(List<Long> ids);

    public void deleteRecipe(Long id);

    public Long addRecipe(JsonElement recipeJson);

    public Long registerUser(String username, String password);

    public Long checkUser(String username, String password);

    public void unregisterUser(Long id);

    public JsonElement getUserInfo(Long id);

    public void indexRecipes();

    public JsonElement searchRecipes(String searchQuery, String field);

}
