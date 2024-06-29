package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.Transaction.Change;

public class TransactionTest {

    private Transaction transaction;
    private Logger logger;

    @Before
    public void setup() {
        logger = new Logger();
        transaction = new Transaction(logger);
    }

    @Test
    public void testGiveChange_ReturnsCorrectChange() {
        int amountFed = 5;
        transaction.feedMoney(amountFed);
        int expectedQuarters = 20;
        int expectedDimes = 0;
        int expectedNickels = 0;

        Change change = transaction.giveChange();

        Assert.assertEquals("Number of quarters should be correct", expectedQuarters, change.getQuarters());
        Assert.assertEquals("Number of dimes should be correct", expectedDimes, change.getDimes());
        Assert.assertEquals("Number of nickels should be correct", expectedNickels, change.getNickels());
        Assert.assertEquals("Balance should be reset to 0", 0.0, transaction.getBalance(), 0.001);
    }

    @Test
    public void testCheckOut_ReturnsFormattedString() {
        Transaction transaction = new Transaction(new Logger());

        double balance = 1.40;
        String expectedOutput = "Quarters: 5, Dimes: 1, Nickels: 1";
        String actualOutput = transaction.checkOut(balance);
        Assert.assertEquals("Formatted string should match expected output", expectedOutput, actualOutput);
    }
}
