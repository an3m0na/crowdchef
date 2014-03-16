package com.crowdchef.datamodel;

public class Recipe {

    private String aRecipeId;
    private String aRecipeTitle;
    private String aRecipeDescription;

    public String getRecipeId()
    {
        return aRecipeId;
    }

    public void setRecipeId(final String aARecipeId)
    {
        aRecipeId = aARecipeId;
    }

    public String getRecipeTitle()
    {
        return aRecipeTitle;
    }

    public void setRecipeTitle(final String aARecipeTitle)
    {
        aRecipeTitle = aARecipeTitle;
    }

    public String getRecipeDescription()
    {
        return aRecipeDescription;
    }

    public void setRecipeDescription(final String aARecipeDescription)
    {
        aRecipeDescription = aARecipeDescription;
    }
}
