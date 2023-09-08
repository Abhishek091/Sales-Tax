package com.abhi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SalesTax {

    public static double calculateSalesTax(double price, double taxRate) {
        double tax = (price * taxRate) / 100;
        // Round up to the nearest 0.05
        return Math.ceil(tax * 20) / 20;
    }

    public static double calculateTotalCost(List<Item> basket) {
        double totalCost = 0;
        for (Item item : basket) {
            totalCost += item.getPrice();
        }
        return totalCost;
    }
    public static void generateReceipt(List<Item> basket) {
        double salesTaxRate = 10; // 10% sales tax rate
        double importDutyRate = 5; // 5% import duty rate

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Receipt:");
        for (Item item : basket) {
            String itemName = item.getName();
            double itemPrice = item.getPrice();
            boolean isExempt = item.isExempt();
            boolean isImported = item.isImported();

            // Calculate sales tax
            double salesTax = isExempt ? 0 : calculateSalesTax(itemPrice, salesTaxRate);

            // Calculate import duty
            double importDuty = isImported ? calculateSalesTax(itemPrice, importDutyRate) : 0;

            // Calculate total cost for the item
            double totalItemCost = itemPrice + salesTax + importDuty;

            // Print item details
            System.out.println(itemName + ": " + df.format(totalItemCost));
        }

        // Calculate and print the total amounts of sales taxes paid
        double totalSalesTax = basket.stream()
                .filter(item -> !item.isExempt())
                .mapToDouble(item -> calculateSalesTax(item.getPrice(), salesTaxRate))
                .sum();

        double totalImportDuty = basket.stream()
                .filter(Item::isImported)
                .mapToDouble(item -> calculateSalesTax(item.getPrice(), importDutyRate))
                .sum();

        double totalTax = totalSalesTax + totalImportDuty;
        System.out.println("Sales Taxes: " + df.format(totalTax));
        System.out.println("Total: " + df.format(calculateTotalCost(basket) + totalTax));
    }

    public static void main(String[] args) {
        // Example shopping basket
        List<Item> basket = new ArrayList<>();
//        basket.add(new Item("Book", 12.49, true, false));
//        basket.add(new Item("Music CD", 14.99, false, false));
//        basket.add(new Item("Chocolate Bar", 0.85, true, false));

//        basket.add(new Item("1 imported box of chocolates", 10.00, true, true));
//        basket.add(new Item("1 imported bottle of perfume", 47.50, false, true));

        basket.add(new Item("1 imported bottle of perfume", 27.99, false, true));
        basket.add(new Item("1 bottle of perfume", 18.99, false, false));
        basket.add(new Item("1 packet of headache pills", 9.75, true, false));
        basket.add(new Item("1 box of imported chocolates", 11.25, true, true));

        // Generate and print the receipt
        generateReceipt(basket);
    }
}
