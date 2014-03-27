package com.crowdchef.webservice;

import com.crowdchef.core.controller.CoreController;
import com.crowdchef.core.controller.CoreControllerFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hibernate.exception.ConstraintViolationException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.servlet.SparkApplication;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebService implements SparkApplication {
    private CoreController controller;

    public String printStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    @Override
    public void init() {

        controller = CoreControllerFactory.getControllerInstance();

        get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "CrowdChefServer is On!";
            }
        });

        get(new Route("/checkRequest") {
            @Override
            public Object handle(final Request request, final Response response) {
                return request.body();
            }
        });

        get(new Route("/checkDatabase") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.listRecipes());
                    result.addProperty("successful", true);
                } catch (Exception e) {
                    result.addProperty("result", printStackTrace(e));
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/listRecipes") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.listRecipes());
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/getRecipeDetails/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.getRecipe(Long.parseLong(request.params("id"))));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        post(new Route("/addRecipe") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    JsonElement element = new JsonParser().parse(request.body());
                    result.addProperty("result", controller.addRecipe(element));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        post(new Route("/updateRecipe") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    JsonElement element = new JsonParser().parse(request.body());
                    controller.updateRecipe(element);
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        post(new Route("/updateIngredients") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    JsonElement element = new JsonParser().parse(request.body());
                    controller.updateIngredients(element);
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/deleteRecipe/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    controller.deleteRecipe(Long.parseLong(request.params("id")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/registerUser/:username/:password") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.addProperty("result", controller.registerUser(request.params("username"), request.params("password")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/checkUser/:username/:password") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.addProperty("result", controller.checkUser(request.params("username"), request.params("password")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/unregisterUser/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    controller.unregisterUser(Long.parseLong(request.params("id")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/getUserInfo/:id") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.getUserInfo(Long.parseLong(request.params("id"))));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/search/:searchQuery/:field") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.searchRecipes(request.params("searchQuery"), request.params("field")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/indexRecipes") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    controller.indexRecipes();
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/rateRecipe/:recipeId/:userId/:rating") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    controller.rateRecipe(Long.parseLong(request.params("recipeId")), Long.parseLong(request.params("userId")),  Integer.parseInt(request.params("rating")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        post(new Route("/assignTaste") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    controller.assignTaste(new JsonParser().parse(request.body()));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/listUsers") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.listUsers());
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/suggestTerm/:term/:field") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.suggestTerm(request.params("term"), request.params("field")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

        get(new Route("/checkTerm/:term/:field") {
            @Override
            public Object handle(final Request request, final Response response) {
                JsonObject result = new JsonObject();
                try {
                    result.add("result", controller.checkTerm(request.params("term"), request.params("field")));
                    result.addProperty("successful", true);
                } catch(ConstraintViolationException e){
                    result.addProperty("result", "Constraint "+e.getConstraintName()+" violated");
                    result.addProperty("successful", false);
                }catch (Exception e) {
                    result.addProperty("result", e.getMessage() == null? printStackTrace(e):e.getMessage());
                    result.addProperty("successful", false);
                }
                return result;
            }
        });

    }
}
