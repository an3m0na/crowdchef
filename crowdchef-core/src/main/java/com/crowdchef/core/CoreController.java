package com.crowdchef.core;


import com.crowdchef.core.handlers.RecipeHandler;
import com.crowdchef.core.handlers.UserHandler;
import com.crowdchef.core.retriever.Indexer;
import com.crowdchef.core.retriever.Searcher;
import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.entities.Recipe;
import com.crowdchef.datamodel.entities.User;
import com.crowdchef.datamodel.entities.UserInfo;
import com.google.gson.*;
import org.json.JSONObject;

import java.util.List;

public class CoreController {
    private CrowdChefDatabase database;
    private UserHandler userHandler;
    private RecipeHandler recipeHandler;
    private final Searcher searcher;
    private final Indexer indexer;

    public CoreController() {
        this.database = new CrowdChefDatabase();
        this.userHandler = new UserHandler(this.database);
        this.recipeHandler = new RecipeHandler(this.database);
        this.searcher = new Searcher();
        this.indexer = new Indexer();
    }

    public static Gson buildGson(final Class<?> skippedFieldClass){
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {

                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }

                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaredClass() == skippedFieldClass;
                    }

                })
                .create();
    }

    public JsonElement listRecipes(){
        Gson gson = new Gson();
        List<Recipe> recipes = recipeHandler.getRecipes();
        return buildGson(User.class).toJsonTree(recipes);
    }

    public JsonElement getUserInfo(Long id) {
        return buildGson(User.class).toJsonTree(userHandler.getUserInfo(id));
    }

    public JsonElement search(String searchQuery, String field) {
        searcher.search(searchQuery, field);
        return null;
    }

}
