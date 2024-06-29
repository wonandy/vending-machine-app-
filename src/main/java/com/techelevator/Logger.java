package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final String LOG_FILE = "Log.txt";
    private static final String SALES_REPORT = "SalesReport.txt";

    public void logFeedMoney(int amount, double newBalance) {
        String formattedAmount = String.format("%.2f", (double) amount);
        String message = String.format("%s FEED MONEY: $%s $%.2f", getCurrentTimestamp(), formattedAmount, newBalance);
        writeToLog(message);
    }

    public void logPurchase(String itemName, String slot, double price, double newBalance) {
        String message = String.format("%s %s %s $%.2f $%.2f", getCurrentTimestamp(), itemName, slot, price, newBalance);
        writeToLog(message);
    }

    public void logGiveChange(Transaction.Change change, double totalChange) {
        String changeString = String.format("%s GIVE CHANGE: $%.2f $%.2f",
                getCurrentTimestamp(),
                totalChange,
                0.00);
        writeToLog(changeString);
    }

    private void writeToLog(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        return sdf.format(new Date());
    }

    public void writeSalesReport(String report) {
        String dateTimeForFile = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
        String fileName = "SalesReport " + dateTimeForFile + ".txt";
        System.out.println(fileName);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}
