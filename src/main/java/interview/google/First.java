package interview.google;

import java.util.*;

    /*You are the head chef for a new restaurant whose menu changes every night. You have a large book of recipes, and you select a different set of recipes each night based on what you can make from the Raw Ingredients that you get each day. Each recipe has two sets of inputs; “Intermediate Ingredients” which are the outputs of other recipes and “Raw Ingredients”. Let's look at a couple example recipes:

    Bread
    Raw Ingredients: Flour, Yeast
    Intermediate Ingredients: NONE

    Ham & Cheese Sandwich
    Raw Ingredients: Ham, Cheese
    Intermediate Ingredients: Bread

    Caprese Salad
    Raw Ingredients: Cheese, Tomato, Basil
    Intermediate Ingredients: NONE

    In order to make a "Ham & Cheese Sandwich" you need to make sure that you have the Flour, Yeast, Ham, and Cheese “Raw Ingredients” available for the day. If you have Ham, Tomato, Flour, Cheese, and Basil, you can only put “Caprese Salad” on the menu.

    Based on a set of recipes and a set of raw ingredients that you have on a given day, can you write an algorithm that could calculate the list of possible recipes that you can put on the menu for the day?*/


public class First {

    private static class Recipe {
        String name;
        List<String> raw_Ingredients;
        List<Recipe> intermediate_Recipes; // Dependent ingredients which are recipes itself.

        public Recipe(String name, List<String> raw_Ingredients, List<Recipe> intermediate_Recipes) {
            this.raw_Ingredients = raw_Ingredients;
            this.intermediate_Recipes = intermediate_Recipes;
            this.name = name;
        }

    }

    Set<Recipe> all_recipes;
    Set<Recipe> result = null;

    First(Set<Recipe> all_recipes) {
        this.all_recipes = all_recipes;
    }

    First() {
        this.all_recipes = new HashSet<>();
        String ingredients[] = {"Yeast", "Flour"};
        all_recipes.add(new Recipe("Bread", Arrays.asList(ingredients), null));
    }

    // Given: Cheese, Tomato, Basil, yeast, Flour, ham cheese
    Set<Recipe> getRecipes(List<String> givenIng) {
        result = new HashSet<>();
        for (Recipe r : all_recipes) {
            if (matchRecipe(r, givenIng)) { // bread
                result.add(r);
            }
        }
        return result;
    }

    Boolean matchRecipe(Recipe recipe, List<String> gIng) {
        if (recipe.raw_Ingredients.containsAll(gIng)) {
            if (recipe.intermediate_Recipes != null) {
                for (Recipe r : recipe.intermediate_Recipes) {
                    if (!result.contains(r)) {
                        if (matchRecipe(r, gIng)) {
                            result.add(r);
                        } else {
                            return false;
                        }

                    }
                }
            }
            return true;
        }
        return false;
    }

    public void printRecipes(Set<Recipe> recipes){
        for(Recipe recipe: recipes){
            System.out.println("------------------------------");
            printRecipe(recipe);
            System.out.println("------------------------------");
        }
    }

    public void printRecipe(Recipe recipe){
        System.out.println("Recipe Name: " + recipe.name);
        System.out.println("Raw Ingredients: " + recipe.raw_Ingredients.toString());
        if(recipe.intermediate_Recipes != null){
            System.out.println("*******************************");
            System.out.println("Intermediate Recipe:");
            for(Recipe iRecipe: recipe.intermediate_Recipes){
                printRecipe(iRecipe);
            }
            System.out.println("*******************************");
        }else {
            System.out.println("No Intermediate Recipe");
        }
    }

    public static void main(String arguments[]){
        String[] ingridents = new String[]{"Yeast","Flour"};
        Set<Recipe> recipeList = new HashSet<>();
        Recipe recipe = new Recipe("Bread", Arrays.asList(ingridents), null);
        recipeList.add(recipe);
        ingridents = new String[]{"Lettuce", "Patty"};
        recipe = new Recipe("Sandwich", Arrays.asList(ingridents), Arrays.asList(recipe));
        recipeList.add(recipe);
        First first = new First(new HashSet<>(recipeList));
        recipeList.clear();
        ingridents = new String[]{"Yeast", "Flour"};
        recipeList = first.getRecipes(Arrays.asList(ingridents));
        first.printRecipes(recipeList);
    }
}
