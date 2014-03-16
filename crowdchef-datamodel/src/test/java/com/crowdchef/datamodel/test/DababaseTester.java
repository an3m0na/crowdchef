package com.crowdchef.datamodel.test;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.DatabaseUtil;
import com.crowdchef.datamodel.entities.Recipe;
import com.crowdchef.datamodel.entities.User;
import com.crowdchef.datamodel.entities.UserInfo;
import org.hibernate.criterion.Restrictions;

import javax.xml.transform.Result;
import java.util.List;


public class DababaseTester {

    public static void populateDatabase(CrowdChefDatabase database){
        User newUser = new User("john", "john");
        UserInfo newUserInfo= new UserInfo(newUser, "john@gmail.com");
        newUserInfo.setAddress("Some Street 124");
        newUserInfo.setCity("Delft");
        newUserInfo.setCountry("Netherlands");
        database.save(newUser, User.class);
        database.save(newUserInfo, UserInfo.class);

        Recipe newRecipe = new Recipe("Perfect recipe", newUser);
        newRecipe.setDescription("Awesome recipe for a recipe");
        newRecipe.setDirections("Stir recipe until becomes recipe");
        newRecipe.setTags("perfect,inception");
        database.save(newRecipe, Recipe.class);
    }

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

        populateDatabase(database);

        List<Recipe> recipes = database.retrieve("AllRecipes", Recipe.class);
        System.out.println(recipes);
    }
}
