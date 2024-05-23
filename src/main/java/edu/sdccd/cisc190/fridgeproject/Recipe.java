package edu.sdccd.cisc190.fridgeproject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe {
    public final String FILE_NAME = "Recipes.txt";
    private String name = "";
    private ArrayList<Ingredient> ingredients ;
    /**
    *Default constructor.
    */
    public Recipe() {
        ingredients = new ArrayList<Ingredient>();
    }
    public Recipe(String n){
        name = n;
        ingredients = new ArrayList<Ingredient>();
    }
    public Recipe(Recipe r){
        name = r.getRecipeName();
        ingredients = r.getIngredients();
    }
    public Recipe(String n, ArrayList<Ingredient> ing){
        name = n;
        ingredients = new ArrayList<Ingredient>();
        for(Ingredient i : ing){
            ingredients.add(i);
        }
    }
    public Recipe(ArrayList<UsersIngredient> uIng){
        ingredients = new ArrayList<>();
        for(UsersIngredient u: uIng){
            ingredients.add(u);
        }
    }
    public void addToFile() throws IOException {
        FileWriter fw = new FileWriter(new File(FILE_NAME), true);
        fw.write(name + "\t");
        for(Ingredient i : ingredients){
            fw.write(i.getName() + "\t" + i.getAmount() + "\t");
        }
        fw.write("\n");
        fw.close();
    }
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(new File(FILE_NAME), false);
        fw.write(name + "\t");
        for(Ingredient i : ingredients){
            fw.write(i.getName() + "\t" + i.getAmount() + "\t");
        }
        fw.write("\n");
        fw.close();
    }
    public int getIngredientNum(){
        return ingredients.size();
    }
    public Ingredient getIngredientAt(int i){
            return ingredients.get(i);

    }
    public void addIngredient(Ingredient i){
        Ingredient i1 = new Ingredient(i.getName(),i.getAmount());
        ingredients.add(i1);
    }
    public int checkSimilarity(Recipe r1) {
        int counter = 0;
        for(Ingredient i : r1.getIngredients()){
            for(Ingredient i1 : ingredients){
                if(i1.getName().equals(i.getName())){
                    counter++;
                }
            }
        }
        return counter;
    }
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ing = new ArrayList<Ingredient>();
        for(Ingredient i : ingredients){
            ing.add(i);
        }
        return ing;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getRecipeName(){
        return name;
    }
    public String toString(){
        int counter = 1;
        String result =name + ":\n";
        for(Ingredient i : ingredients){
            result += counter +  ". " + i.toString() + "\n"; 
            counter++;
        }
        return result;
    }
    public int getIngredientIndex(String s){
        for(int i = 0; i < ingredients.size();i++){
            if(ingredients.get(i).getName().equals(s)){
                return i;
            }
        }
        return -1;
    }
}
