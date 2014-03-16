package com.crowdchef.webservice;

import com.crowdchef.core.CoreController;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class WebService implements SparkApplication {
    private CoreController controller;

    @Override
    public void init() {

        controller = new CoreController();

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

        get(new Route("/getUserInfo/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                return controller.getUserInfo(Long.parseLong(request.params("id")));
            }
        });

        get(new Route("/search/:searchQuery/:field") {
            @Override
            public Object handle(final Request request, final Response response) {

                controller.search(request.params("searchQuery"),
                        request.params("field"));
                return "CrowdChefServer is On!";
            }
        });

    }
}
