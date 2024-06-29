package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationTest extends TestCase {
    private Inventory inventory;
    private Transaction transaction;
    private Logger logger;
    private Application application;
    @Before
    public void setUp() {
        logger = new Logger();
        inventory = new Inventory("vendingmachine.csv");
        transaction = new Transaction(logger);
        application = new Application();
        application.inventory = inventory;
        application.transaction = transaction;
        application.logger = logger;
    }
    @Test
    public void testDisplayMainMenu() {
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // We need a new scanner with the altered input stream
        application.scanner = new Scanner(System.in);
        application.displayMainMenu();
        // We don't expect any specific interactions, so we just ensure it runs without errors
        Assert.assertTrue(true);
    }
    @Test
    public void testPerformPurchase_FeedMoney() {
        String input = "2\n1\n5\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // We need a new scanner with the altered input stream
        application.scanner = new Scanner(System.in);
        // To check feedMoney(), we simulate the input
        application.displayMainMenu();
        // Check the balance after feeding money
        Assert.assertEquals(5.0, transaction.getBalance(), 0.001);
    }

    @Test
    public void testFinishTransaction() {
        String input = "2\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // We need a new scanner with the altered input stream
        application.scanner = new Scanner(System.in);
        // Feed some money first
        transaction.feedMoney(2);
        application.displayMainMenu();
        // Check if the transaction was finished and balance reset
        Assert.assertEquals(0.0, transaction.getBalance(), 0.001);
    }
}
