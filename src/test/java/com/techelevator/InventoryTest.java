package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory("Test-vendingmachine.csv");
    }

    @Test

    public void testProductLocationMatchesItem() {
        String expectedSlot = "A1";
        String expectedName = "Potato Crisps";

        Item item = inventory.getItem(expectedSlot);

        Assert.assertNotNull("Item should not be null", item);
        Assert.assertEquals("Slot should match", expectedSlot, item.getSlotLocation());
        Assert.assertEquals("Item name should match", expectedName, item.getName());
    }

    @Test
    public void testDispenseItem_DecreaseStock() {
        String slot = "A1";
        Item itemBeforeDispense = inventory.getItem(slot);
        int expectedStock = itemBeforeDispense.getStock() - 1;

        inventory.dispenseItem(slot);
        Item itemAfterDispense = inventory.getItem(slot);

        Assert.assertEquals("Stock should decrease by 1 after dispensing", expectedStock, itemAfterDispense.getStock());
    }
}
