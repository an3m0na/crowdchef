package com.crowdchef.core.test;


import com.crowdchef.core.controller.CoreController;
import com.crowdchef.core.controller.CoreControllerFactory;

public class CoreControllerTester {
    public static void main(String[] args) {
        CoreController controller = CoreControllerFactory.getControllerInstance();
        System.out.println(controller.getUserInfo(new Long(21)));
        System.out.println(controller.getRecipe(new Long(6)));
        controller.indexRecipes();
        controller.searchRecipes("perfect", "name");
    }
}
