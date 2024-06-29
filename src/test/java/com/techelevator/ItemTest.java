package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest extends TestCase {
    public Item vendingProducts = new Item();



    @Test
    public void testIsInStock() {
        vendingProducts.setStock(5);
        Assert.assertTrue(vendingProducts.isInStock());
    }
    @Test
    public void test_zero_inventory(){
        vendingProducts.setStock(0);
        Assert.assertFalse(vendingProducts.isInStock());
    }
}