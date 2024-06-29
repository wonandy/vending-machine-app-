package com.techelevator;

public class Transaction {

    private double balance;
    private Logger logger;


    public Transaction(Logger logger) {
        this.balance = 0.0;
        this.logger = logger;
    }

    public static class Change {


        private int quarters;
        private int dimes;
        private int nickels;

        public Change(int quarters, int dimes, int nickels) {
            this.quarters = quarters;
            this.dimes = dimes;
            this.nickels = nickels;
        }

        public int getQuarters() {
            return quarters;
        }

        public int getDimes() {
            return dimes;
        }

        public int getNickels() {
            return nickels;
        }



    }
    public void feedMoney(int amount) {
        balance += amount;
        logger.logFeedMoney(amount, balance);
    }

    public void purchaseItem(Item item, double price) {
        if (balance >= price) {
            balance -= price;
            logger.logPurchase(item.getName(), item.getSlotLocation(), item.getPrice(), balance);
        } else {
            System.out.println("Insufficient funds to complete the purchase.");
        }
    }

    public Change giveChange() {
        int quarters = (int) (balance / 0.25);
        balance -= quarters * 0.25;
        int dimes = (int) (balance / 0.10);
        balance -= dimes * 0.10;
        int nickels = (int) (balance / 0.05);
        balance -= nickels * 0.05;

        Change change = new Change(quarters, dimes, nickels);
        logger.logGiveChange(change, balance + quarters * 0.25 + dimes * 0.10 + nickels * 0.05);
        resetBalance();
        return change;

    }

    public double getBalance() {
        return balance;
    }

    public void resetBalance() {
        balance = 0.0;
    }


    public String checkOut(double balance){
        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;

        int balanceInCents = (int) Math.round(balance * 100);

        int quarters = balanceInCents / QUARTER;
        balanceInCents %= QUARTER;

        int dimes = balanceInCents / DIME;
        balanceInCents %= DIME;

        int nickels = balanceInCents / NICKEL;
        balanceInCents %= NICKEL;

        return String.format("Quarters: %d, Dimes: %d, Nickels: %d", quarters, dimes, nickels);
    }
}