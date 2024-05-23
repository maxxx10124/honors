package edu.sdccd.cisc190.fridgeproject;
class Ingredient
{
    private String name = "";
    private double amount = 0.0;
    public Ingredient(){
    }
    public Ingredient(String n){
        name = n;
    }
    public Ingredient(String n, double a){
        name = n;
        amount  = a;
    }
    public String getName()
    {
        return name;
    }
    public void setAmount(double d){
        amount  = d;
    }
    public double getAmount(){
        return amount;
    }
    public void setName(String n)
    {
        name = n;
    }
    public String toString(){
        return "Ingredient name: "+ name + ", amount: "+ amount + " grams. ";
    }
}
