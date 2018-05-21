package com.tsguild.foundations.variables;

public class MenuOfChampions {
    public static void main(String[] args) {
        String pizza, pie, omelet;
        float pizzaPrice, piePrice, omeletPrice;

        pizza = "Big Rico";
        pie = "Invisible Strawberry";
        omelet = "Denver";

        pizzaPrice = 500.00f;
        piePrice = 2.00f;
        omeletPrice = 1.50f;

        System.out.println("WELCOME TO RESTAURANT NIGHT VALE!");
        System.out.println("\t" + "Today's Menu Is...");

        System.out.println("Slice of " + pizza + " Pizza \t $" + pizzaPrice);
        System.out.println(pie + " Pie\t $" + piePrice);
        System.out.println(omelet + " Omelet\t\t\t\t $" + omeletPrice);
    }
}
