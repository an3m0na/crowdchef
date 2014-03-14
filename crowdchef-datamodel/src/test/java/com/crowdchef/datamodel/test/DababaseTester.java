package com.crowdchef.datamodel.test;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.DatabaseUtil;
import com.crowdchef.datamodel.entities.Recipe;
import org.hibernate.criterion.Restrictions;

import javax.xml.transform.Result;
import java.util.List;


public class DababaseTester {
    public  static void main (String[] args){
        CrowdChefDatabase database = new CrowdChefDatabase();


//        List<Result> results=
//                database.retrieve("query1", "name", "perfect recipe for succes", Result.class);
//        List<Result> results2=
//                database.retrieve("query2", Result.class);
//        Recipe recipe = (Recipe)DatabaseUtil.getSession().createCriteria(Recipe.class).add(Restrictions.eq("name","perfect recipe for succes")).uniqueResult();
//        List<Recipe> recipes= DatabaseUtil.getSession().createCriteria(Recipe.class).add(Restrictions.eq("name","perfect recipe for succes")).list();
//        for(Recipe r: recipes){
//            System.out.println("recipe id: " +r.getId());
//        }
//        System.out.println(recipe.getId());

        List<Recipe> recipes = database.retrieve("AllRecipesLikeName", "name", "perfect recipe for succes", Recipe.class);
        System.out.println(recipes);
        Recipe newRecipe = new Recipe("Second recipe");
        database.save(newRecipe, Recipe.class);
        recipes = database.retrieve("AllRecipesLikeName", "name", "perfect recipe for succes", Recipe.class);
        System.out.println(recipes);
    }
}
