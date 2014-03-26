package com.crowdchef.datamodel.test;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.daos.RecipeDAO;
import com.crowdchef.datamodel.daos.UserDAO;
import com.crowdchef.datamodel.entities.Recipe;
import com.crowdchef.datamodel.entities.User;
import org.hibernate.exception.ConstraintViolationException;


public class DababaseTester {

    public static void repopulateDatabase(CrowdChefDatabase database) {

        RecipeDAO recipeDAO = new RecipeDAO(database);
        UserDAO userDAO = new UserDAO(database);

        recipeDAO.deleteAllRecipes();
        userDAO.deleteAllUsers();

        User user = userDAO.updateUser((Long) null, "john", "john");
        userDAO.updateUserInfo(user, "john@gmail.com", "Some Street 124", "Delft", "Netherlands");
        //userDAO.updateUser(user, "john", "johnson");
        userDAO.updateUserInfo(user.getId(), "john@gmail.com", "Some Street 124", "Rotterdam", "Netherlands");

        Recipe recipe = recipeDAO.updateRecipe(null, "Perfect recipe", "Awesome recipe for a recipe", "Stir recipe until becomes recipe", "perfect,inception", null, user);
        recipeDAO.addIngredient(recipe, "tomato sauce", "spicy tomato sauce is best", "200 mL", 2);
        recipeDAO.addIngredient(recipe, "chicken breast", null, "400 g", 1);
    }

    public static void main(String[] args) {
        CrowdChefDatabase database = new CrowdChefDatabase();

        RecipeDAO recipeDAO = new RecipeDAO(database);
        UserDAO userDAO = new UserDAO(database);


//        List<Result> results=
//                database.retrieve("query1", "name", "perfect recipe for succes", Result.class);
//        List<Result> results2=
//                database.retrieve("query2", Result.class);
        //Recipe recipe = (Recipe)DatabaseUtil.getSession().createCriteria(Recipe.class).add(Restrictions.eq("id", 11L)).uniqueResult();
//        List<Recipe> recipes= DatabaseUtil.getSession().createCriteria(Recipe.class).add(Restrictions.eq("name","perfect recipe for succes")).list();
//        for(Recipe r: recipes){
//            System.out.println("recipe id: " +r.getRatingId());
//        }
        //   System.out.println(recipe.getRating());

        //repopulateDatabase(database);
        try {
            recipeDAO.assignTaste(2L, 3, 4, 4, 1, 3);
            System.out.println(recipeDAO.getRecipe(2L).getTasteScore().getSavory());
        } catch (ConstraintViolationException e) {
            System.out.println("Constraint " + e.getConstraintName() + " violated");
        }

//
//
//        Recipe recipe =  recipeDAO.getRecipe(new Long(11));
//       recipeDAO.deleteIngredients(recipe);
//
//        recipeDAO.addIngredients(recipe, Arrays.asList(new Ingredient(recipe, "bla")));

            //  System.out.println(recipeDAO.getRecipe(11L).getRating());
            //Query query = DatabaseUtil.getSession().createSQLQuery("SELECT COALESCE((SELECT avg(rating) FROM recipe_rating WHERE recipe_id = a.id), 0) rating, a.* FROM recipe a WHERE id = :id").setParameter("id", 11L);
            //System.out.println(query.li);

        }
    }
