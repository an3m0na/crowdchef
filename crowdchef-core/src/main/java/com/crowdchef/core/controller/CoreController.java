package com.crowdchef.core.controller;


import com.google.gson.JsonElement;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

public interface CoreController {


    public JsonElement getRecipe(Long id);

    public JsonElement listRecipes();

    public JsonElement listRecipes(boolean shortForm);

    public JsonElement listRecipes(List<Long> ids);

    public JsonElement listRecipes(List<Long> ids, boolean shortForm);

    public void deleteRecipe(Long id);

    public Long addRecipe(JsonElement recipeJson);

    public void updateRecipe(JsonElement recipeJson);

    public void updateIngredients(JsonElement recipeJson);

    public Long registerUser(String username, String password);

    public Long checkUser(String username, String password);

    public void unregisterUser(Long id);

    public JsonElement getUserInfo(Long id);

    public void indexRecipes() throws IOException;

    public JsonElement searchRecipes(String searchQuery, String field) throws IOException, ParseException;

    public void rateRecipe(Long recipeId, Long userId, Integer rating);

    public void assignTaste(JsonElement tasteScoreJson);

    public JsonElement listUsers();

    public JsonElement suggestTerm(String term, String field);

    public JsonElement checkTerm(String term, String field) throws IOException;

}
