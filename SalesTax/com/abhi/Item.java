package com.abhi;

public class Item {
    private String name;
    private double price;
    private boolean isExempt;
    private boolean isImported;

    public Item(String name, double price, boolean isExempt, boolean isImported) {
        this.name = name;
        this.price = price;
        this.isExempt = isExempt;
        this.isImported = isImported;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isExempt() {
        return isExempt;
    }

    public boolean isImported() {
        return isImported;
    }
}
