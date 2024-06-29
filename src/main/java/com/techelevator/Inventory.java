package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory {
    private Map<String, Item> items;


    public Inventory(String filePath) {
        items = new TreeMap<>();
        loadItems(filePath);
    }

    File file = new File("vendingmachine.csv");
    private void loadItems(String filePath) {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");
                String slot = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String type = parts[3];
                items.put(slot, new Item(slot, name, price, type));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Inventory file not found.");
        }
    }
    public void displayItems() {
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            Item item = entry.getValue();
            System.out.println(item.getSlotLocation() + " | " + item.getName() + " | $" + String.format("%.2f", item.getPrice())
                    + " | " + item.getType() + " | " + (item.getStock() > 0 ? item.getStock() : "SOLD OUT"));
        }
    }

    public Item getItem(String slot) {
        return items.get(slot.toUpperCase());
    }

    public void dispenseItem(String slot) {
        Item item = items.get(slot.toUpperCase());
        if (item != null && item.getStock() > 0) {
            item.setStock(item.getStock() - 1);
            System.out.println("Dispensing: " + item.getName());
        }
    }

    public Map<String, Item> getItems() {
        return items;
    }

}