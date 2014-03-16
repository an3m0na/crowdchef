package com.crowdchef.core;


import com.crowdchef.datamodel.CrowdChefDatabase;

public class DatabaseController {
    private CrowdChefDatabase database;
    private UserHandler userHandler;
    private RecipeHandler recipeHandler;

    public DatabaseController() {
        this.database = new CrowdChefDatabase();
        this.userHandler = new UserHandler(this);
        this.recipeHandler = new RecipeHandler(this);
    }


    public CrowdChefDatabase getDatabase() {
        return database;
    }

    public UserHandler getUserHandler() {
        return userHandler;
    }

    public RecipeHandler getRecipeHandler() {
        return recipeHandler;
    }
}
