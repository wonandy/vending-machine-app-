package com.techelevator;

public class Item {
    private String slotLocation;
    private String name;
    private double price;
    private String type;
    private int stock;
    public static final int MAX_STOCK = 5;

    public Item(String slotLocation, String name, double price, String type) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = MAX_STOCK;
    }

    public String getSlotLocation() {
        return slotLocation;
    }
    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isInStock() {
        return this.stock > 0;
    }

    public String getDispenseMessage() {
        switch (this.type.toLowerCase()) {
            case "chip":
                return "Crunch Crunch, Yum!";
            case "candy":
                return "Munch Munch, Yum!";
            case "drink":
                return "Glug Glug, Yum!";
            case "gum":
                return "Chew Chew, Yum!";
            default:
                return "Enjoy your snack!";
        }
    }

    public Item(){}

}