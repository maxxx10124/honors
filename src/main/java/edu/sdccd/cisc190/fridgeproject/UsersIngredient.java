package edu.sdccd.cisc190.fridgeproject;
public class UsersIngredient extends Ingredient{
    private boolean hasEnough;
    private double onHand;
    public UsersIngredient(String s, double amount, double d){
        super(s,amount);
        onHand = d;
        hasEnough = d >= amount;
    }
    public UsersIngredient(String s, double d){
        super(s);
        onHand = d;
    }
    public boolean getHasEnough(){
        return hasEnough;
    }
    public double getOnHand(String s){
        return onHand;
    }
    public double getOnHand(){
        return onHand;
    }
    public void setHasEnough(boolean d){
        hasEnough = d;
    }
    public void setAmount(double a){
        super.setAmount(a);
        hasEnough = onHand >= a;
    }
    
    public String toString(){
        if(this.getAmount() == 0){
            return "Ingredient not included in the saved recipe.";
        }
        else if(hasEnough){
            return super.toString()+ "You have: "+ onHand+ "which is enough.";
        }
        else{
            return super.toString() + "You have: "+ onHand+ "which is not enough.";
        }
    }
}
