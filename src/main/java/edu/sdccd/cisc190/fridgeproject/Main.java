package edu.sdccd.cisc190.fridgeproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        Ingredient i1 = new Ingredient();
        int choice;
        Scanner keyboard = new Scanner(System.in);
        final String FILE_NAME = "Recipes.txt";
        System.out.println("Hello, welcome to fridge project.\n");
        while(true){
            System.out.println("\n1. View saved recipes.\n2. Add new recipe.\n3. Change saved recipe.\n4. Search recipe based on the ingredient you have.\n5. Unit converter.\n6. Reset all saved recipes\n7. Exit.");
            while(true){
                try{
                    choice = keyboard.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input, please try again:");
                    keyboard.nextLine();
                    continue;
                }
                if(choice < 1 || choice > 7){
                    System.out.println("Not one of the choices, please try again: ");
                    continue;
                }
                else{
                    break;
                }
            }
            if(choice == 7){
                break;
            }
            switch (choice) {
                case 1:
                recipes = new ArrayList<Recipe>();
                Scanner scan = new Scanner(new File(FILE_NAME));
                String s= "";
                double d = 0.0;
                boolean eof = false;
                if(scan.hasNext()){
                    s = scan.next();   
                }
                else{
                    eof = false;
                }
                while(!eof){
                    Recipe r1 = new Recipe();
                    try{
                        r1.setName(s);
                        while(true){
                            s = scan.next();
                            i1.setName(s);
                            d = scan.nextDouble();
                            i1.setAmount(d);
                            r1.addIngredient(i1);
                        }
                    }
                    catch(InputMismatchException e){
                        recipes.add(r1);

                    }
                    catch(NoSuchElementException e){
                        eof = true;
                    }
                }
                if(recipes.size() > 1){
                    if(!s.equals(recipes.get(recipes.size()-1).getIngredientAt(recipes.get(recipes.size()-1).getIngredientNum()-1).getName())){
                        Recipe r1 = new Recipe();
                        r1.setName(s);
                        recipes.add(r1);
                    }
                }
                else if(s.trim().length() != 0){
                    Recipe r1 = new Recipe();
                        r1.setName(s);
                        recipes.add(r1);
                }
                
                if(recipes.size() == 0){
                    System.out.println("There are no saved recipes, please save some so you can view next time.");
                }
                else{
                    for(int i = 0; i < recipes.size(); i ++){
                        System.out.println("Recipe "+ (i+1) +": " +recipes.get(i));
                    }
                }
                
                scan.close();
                    break;
            
                case 2:
                keyboard.nextLine();
                System.out.print("\nPlease enter Recipe name: ");
                Recipe newRec = new Recipe(keyboard.nextLine());
                String ingreName;
                double ingreAmount;
                for(int i =1; i>0; i++){
                    System.out.println("Please enter inngredient number "+ i+", or enter -1 to stop: ");
                    ingreName = keyboard.next();
                    if(ingreName.equals("-1")){
                        break;
                    }
                    else{
                        System.out.println("Please enter the amount needed for this ingredient: ");
                        while(true){
                            try{
                                keyboard.nextLine();
                                ingreAmount = keyboard.nextDouble();
                                if(ingreAmount <= 0){
                                    System.out.println("Invalid amount of ingredient entered, please try again.");
                                    continue;
                                }
                                break;
                            }
                            catch(InputMismatchException e){
                                System.out.println("Invalid entry, please try again.");
                                continue;
                            }
                        }
                        newRec.addIngredient(new Ingredient(ingreName,ingreAmount));
                    }
                }
                newRec.addToFile();
                System.out.println("Successfully added to saved recipes.");
                    break;
                case 3:
                int recipeIndex;
                recipes = new ArrayList<Recipe>();
                scan = new Scanner(new File(FILE_NAME));
                s= "";
                d = 0.0;
                eof = false;
                if(scan.hasNext()){
                    s = scan.next();   
                }
                else{
                    eof = false;
                }
                while(!eof){
                    Recipe r1 = new Recipe();
                    try{
                        r1.setName(s);
                        while(true){
                            s = scan.next();
                            i1.setName(s);
                            d = scan.nextDouble();
                            i1.setAmount(d);
                            r1.addIngredient(i1);
                        }
                    }
                    catch(InputMismatchException e){
                        recipes.add(r1);
                    }
                    catch(NoSuchElementException e){
                        eof = true;
                    }
                    
                }
                if(recipes.size() > 1){
                    if(!s.equals(recipes.get(recipes.size()-1).getIngredientAt(recipes.get(recipes.size()-1).getIngredientNum()-1).getName())){
                        Recipe r1 = new Recipe();
                        r1.setName(s);
                        recipes.add(r1);
                    }
                }
                else if(s.trim().length() != 0){
                    Recipe r1 = new Recipe();
                        r1.setName(s);
                        recipes.add(r1);
                }
                if(recipes.size() == 0){
                    System.out.println("The saved recipes is empty, there's nothing to change.");
                }
                else{
                    System.out.println("Here's the list of saved recipes now:");
                    for(int i = 0; i < recipes.size(); i ++){
                        System.out.println("Recipe "+ (i+1) +": " +recipes.get(i));
                    }
                    System.out.println("Which one would you like to change?");
                    while(true){
                        try{
                            keyboard.nextLine();
                            choice = keyboard.nextInt();
                            recipes.get(choice-1);
                            recipeIndex= choice - 1;
                            break;
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please put in a valid number.");
                        }
                        catch(IndexOutOfBoundsException e){
                            System.out.println("That's not a valid index number, please try again.");
                        }
                        catch(NoSuchElementException e){
                            continue;
                        }
                    }
                    System.out.println("Would you like to change:\n1. The name of the recipe?\n2. One of the ingredients?");
                    while(true){
                        try{
                            keyboard.nextLine();
                            choice = keyboard.nextInt();
                            if(choice<3 && choice > 0){
                                break;
                            }
                            else{
                                System.out.println("That's not one of the choices, please try again.");
                                continue;
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please put in a valid number.");
                        }
                        catch(NoSuchElementException e){
                            continue;
                        }
                    }
                    if(choice == 1){
                        System.out.println("What would be the new name of the recipe?");
                        s = keyboard.next();
                        recipes.get(recipeIndex).setName(s);
                    }
                    else{
                        System.out.println("Which ingredient would you like to change?\n" + recipes.get(recipeIndex));
                        int ingredientIndex;
                        while(true){
                            try{
                                keyboard.nextLine();
                                choice= keyboard.nextInt();
                                recipes.get(recipeIndex).getIngredientAt(choice - 1);
                                ingredientIndex = choice - 1;
                                break;
                            }
                            catch(IndexOutOfBoundsException e){
                                System.out.println("That's not a valid index for the ingredient.");
                            }
                            catch(InputMismatchException e){
                                System.out.println("Please put in a valid number");
                            }
                            catch(NoSuchElementException e){
                                continue;
                            }
                        }
                        System.out.println("Do you want to change the ingredient name or the amount?\n1. The name.\n2. The amount.");
                        while (true) {
                            try{
                                keyboard.nextLine();
                                choice= keyboard.nextInt();
                                if(choice >0 && choice < 3){
                                    break;
                                }
                                else{
                                    System.out.println("That's not one of the choices, please try again.");
                                }
                            }
                            catch(InputMismatchException e){
                                System.out.println("Please put in a valid number.");
                            }
                            catch(NoSuchElementException e){
                                continue;
                            }
                        }
                        if(choice == 1){
                            System.out.println("What would be the new name of the ingredient?");
                            s = keyboard.next();
                            recipes.get(recipeIndex).getIngredientAt(ingredientIndex).setName(s);
                        }
                        else{
                            System.out.println("Please enter new amount:");
                            while (true) {
                                try{
                                    keyboard.nextLine();
                                    d = keyboard.nextDouble();
                                    if(d <= 0){
                                        System.out.println("Invalid amount of ingredient entered, please try again.");
                                        continue;
                                    }
                                    break;
                                }
                                catch(InputMismatchException e){
                                    System.out.println("Please put in a valid number.");
                                }
                                catch(NoSuchElementException e){
                                    continue;
                                }
                            }
                            recipes.get(recipeIndex).getIngredientAt(ingredientIndex).setAmount(d);
                        }
                        
                    }
                    FileWriter f = new FileWriter(FILE_NAME);
                    f.close();
                        for(Recipe rec: recipes){
                            rec.addToFile();
                        }
                        System.out.println("Sucessfully changed saved recipes.");
                }
                
                    break;
                case 4:
                recipes = new ArrayList<Recipe>();
                scan = new Scanner(new File(FILE_NAME));
                s= "";
                d = 0.0;
                eof = false;
                ArrayList<UsersIngredient> userRec = new ArrayList<UsersIngredient>();
                for(int i = 1; i > 0; i++){
                    if(scan.hasNext()){
                        s = scan.next();   
                    }
                    else{
                        eof = false;
                    }
                    while(!eof){
                        Recipe r1 = new Recipe();
                        try{
                            r1.setName(s);
                            while(true){
                                s = scan.next();
                                i1.setName(s);
                                d = scan.nextDouble();
                                i1.setAmount(d);
                                r1.addIngredient(i1);
                            }
                        }
                        catch(InputMismatchException e){
                            recipes.add(r1);
                        }
                        catch(NoSuchElementException e){
                            eof = true;
                        }
                        
                    }
                    if(recipes.size() > 0){
                        if(!s.equals(recipes.get(recipes.size()-1).getIngredientAt(recipes.get(recipes.size()-1).getIngredientNum()-1).getName())){
                            Recipe r1 = new Recipe();
                            r1.setName(s);
                            recipes.add(r1);
                        }
                    }
                    else if(s.trim().length() != 0){
                        Recipe r1 = new Recipe();
                            r1.setName(s);
                            recipes.add(r1);
                    }
                    if(recipes.size() == 0){
                        System.out.println("There's currently no saved recipes, and nothing to search from.");
                    }
                    else{
                        keyboard.nextLine();
                    System.out.println("Please enter the name for your ingredient number " + i + "(or enter -1 to stop): ");
                    s = keyboard.next();
                    if(s.equals("-1")){
                        break;
                    }
                    System.out.println("Please enter how many grams you have for the ingredient you just entered: ");
                    while(true){
                        try{
                            keyboard.nextLine();
                            d = keyboard.nextDouble();
                            if(d <= 0){
                                System.out.println("Invalid amount of ingredient entered, please try again.");
                                continue;
                            }
                            userRec.add(new UsersIngredient(s,d));
                            break;
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please put in a valid number");
                        }
                    }
                }
                
                int highestMatch = -1;
                int highestMatchIndex = -1;
                for(int j = 0; j < recipes.size(); j ++){
                    if(new Recipe(userRec).checkSimilarity(recipes.get(j))>highestMatch){
                        highestMatchIndex = j;
                        highestMatch = new Recipe(userRec).checkSimilarity(recipes.get(j));
                    }
                }
                if(highestMatch == -1){
                    System.out.println("Sorry, here are not saved recipes that matches the ingredients you have.");
                }
                else{
                    int savedIngredientIndex;
                    for(int j = 0; j < userRec.size(); j++ ){
                        
                        savedIngredientIndex = recipes.get(highestMatchIndex).getIngredientIndex(userRec.get(j).getName());
                        if(savedIngredientIndex != -1){
                            userRec.get(j).setAmount(recipes.get(highestMatchIndex).getIngredientAt(savedIngredientIndex).getAmount());
                        }
                    }
                    System.out.println("The recipe with the highest amoung of matching ingredients is:");
                    System.out.println(recipes.get(highestMatchIndex));
                    System.out.println("\n Your ingredients:");
                    for(int j = 0; j < userRec.size(); j ++){
                        if(userRec.get(i).getAmount() == 0){
                            System.out.println((j+1)+ ". "+userRec.get(j).getName() + " is not included in the saved recipe.");
                            continue;
                        }
                        System.out.println((j+1) + ". "+ userRec.get(j).getName()+ ". Amount needed: " + userRec.get(j).getAmount() + ". And you have: " + userRec.get(j).getOnHand("S") + ". Has enough? " + userRec.get(j).getHasEnough());
                    }
                }
                
                    }
                    
                    break;
                case 5:
                double result = -1;
                System.out.println("Would you like to conver grams to:\n1. Ounces?\n2. Pounds?\n3. Kilograms?");
                while(true){
                    try{
                        keyboard.nextLine();
                        choice = keyboard.nextInt();
                        if(choice > 3 || choice < 1){
                            System.out.println("That's not one of the choices, please try again.");
                            continue;
                        }
                        break;
                    }
                    catch(InputMismatchException e){
                        System.out.println("Please enter a valid choice.");
                    }
                }
                System.out.println("Please enter the amount you would like to convert:");
                while(true){
                    try{
                        keyboard.nextLine();
                        d = keyboard.nextDouble();
                        break;
                    }
                    catch(InputMismatchException e){
                        System.out.println("Please enter a valid number.");
                    }
                }
                switch (choice) {
                    case 1:
                    UnitConverter toOunce = new UnitConverter() {
                        public double convert(double d){
                            d /= 28.3495;
                            int i = (int)(d*100 + .5);
                            d = (double) i / 100.0;
                            return d; 
                        }
                        
                    };
                    result = toOunce.convert(d);
                    System.out.println(d + " grams is equal to " + result + " ounces.");
                        break;
                
                    case 2:
                    
                    UnitConverter toPound = new UnitConverter() {
                        public double convert(double d){
                            d /= 453.592;
                            int i = (int)(d*100 + .5);
                            d = (double) i / 100.0;
                            return d; 
                        }
                    };
                    result = toPound.convert(d);
                    System.out.println(d + " grams is equal to " + result + " pounds.");
                        break;
                    case 3:
                    
                    UnitConverter toKiloGram = new UnitConverter() {
                        public double convert(double d){
                            d /= 1000.00;
                            int i = (int)(d*100 + .5);
                            d = (double) i / 100.0;
                            return d;
                        }
                    };
                    result = toKiloGram.convert(d);
                    System.out.println(d + " grams is equal to " + result + " kilograms.");
                        break;
                }
                    break;
                case 6:
                FileWriter f = new FileWriter(FILE_NAME);
                f.close();
                System.out.println("All saved recipes cleared.");
                    break;
                
            }
        }
        System.out.println("Thank you, see you next time.");
        keyboard.close();
    }
}
