package com.crowdchef.webservice;

import com.crowdchef.core.controller.CoreController;
import com.crowdchef.core.controller.CoreControllerFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class WebService implements SparkApplication {
    private CoreController controller;

    @Override
    public void init() {

        controller = CoreControllerFactory.getControllerInstance();

        get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "CrowdChefServer is On!";
            }
        });

        get(new Route("/listRecipes") {
            @Override
            public Object handle(final Request request, final Response response) {
                return controller.listRecipes();
            }
        });

        get(new Route("/getRecipeDetails/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                return controller.getRecipe(Long.parseLong(request.params("id")));
            }
        });

        get(new Route("/registerUser/:username/:password") {
            @Override
            public Object handle(final Request request, final Response response) {
                return controller.registerUser(request.params("username"), request.params("password"));
            }
        });

        get(new Route("/unregisterUser/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                controller.unregisterUser(Long.parseLong(request.params("id")));
                return "Success";
            }
        });

        get(new Route("/getUserInfo/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                return controller.getUserInfo(Long.parseLong(request.params("id")));
            }
        });

        get(new Route("/search/:searchQuery/:field") {
            @Override
            public Object handle(final Request request, final Response response) {

                return controller.searchRecipes(request.params("searchQuery"),
                        request.params("field"));
            }
        });

    }
}
