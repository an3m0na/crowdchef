package com.crowdchef.core.test;


import com.crowdchef.core.controller.CoreController;
import com.crowdchef.core.controller.CoreControllerFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CoreControllerTester {
    public static void main(String[] args) {
        CoreController controller = CoreControllerFactory.getControllerInstance();
//        System.out.println(controller.getUserInfo(new Long(21)));
//        System.out.println(controller.getRecipe(new Long(6)));
//        System.out.println(controller.checkUser("john", "johny"));
        //controller.indexRecipes();
//        System.out.println(controller.searchRecipes("inception", "tags"));

//        JsonParser parser = new JsonParser();
//        JsonObject obj = (JsonObject)parser.parse("{\"id\":10,\"name\":\"Perfect recipe\",\"description\":\"Awesome recipe for a recipe\",\"tags\":\"perfect,inception\",\"directions\":\"Stir recipe until becomes recipe\",\"createTime\":\"Mar 19, 2014 2:25:32 PM\",\"ingredients\":[{\"id\":20,\"name\":\"chicken breast\",\"quantity\":\"400 g\",\"ord\":1},{\"id\":19,\"name\":\"tomato sauce\",\"description\":\"spicy tomato sauce is best\",\"quantity\":\"200 mL\",\"ord\":2}], \"userId\":1}");
//        controller.addRecipe(obj);
//        System.out.println(controller.listRecipes(null, true));
//        System.out.println(new Gson().toJsonTree(new Long(2)));

        JsonParser parser = new JsonParser();
        //JsonObject obj = (JsonObject) parser.parse("{\"recipeId\":11, \"ingredients\":[{\"id\":20,\"name\":\"carrots\",\"quantity\":\"400 g\",\"ord\":1}]}");
 //controller.updateIngredients(obj);
        JsonObject obj = (JsonObject) parser.parse("{\"id\":11,\"name\":\"Regular donner\",\"description\":\"Alleen vlees\",\"tags\":\"donner,kebap,vlees\",\"directions\":\"Just ask the Turkish guy\",\"createTime\":\"Mar 19, 2014 2:25:32 PM\",\"ingredients\":[{\"id\":20,\"name\":\"chicken breast\",\"quantity\":\"400 g\",\"ord\":1},{\"id\":19,\"name\":\"tomato sauce\",\"description\":\"spicy tomato sauce is best\",\"quantity\":\"200 mL\",\"ord\":2}], \"userId\":10}\n");
controller.updateRecipe(obj);

    }
}
