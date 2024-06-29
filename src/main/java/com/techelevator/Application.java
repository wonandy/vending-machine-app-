package com.techelevator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class Application {
	public Inventory inventory;
	public Transaction transaction;
	public Logger logger;
	public Scanner scanner;

	public Application() {
		this.logger = new Logger();
		this.inventory = new Inventory("vendingmachine.csv");
		this.transaction = new Transaction(logger);
		this.scanner = new Scanner(System.in);

	}

	public void displayMainMenu() {
		while (true) {
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			System.out.println("Please select an option: ");

			String choice = scanner.nextLine();

			switch (choice) {
				case "1":
					inventory.displayItems();
					break;
				case "2":
					performPurchase();
					break;
				case "3":
					System.out.println("Thank you!");
					return;
				default:
					if (isSpecialCommand(choice)) {
						displaySalesReport();
					} else {
						System.out.println("Invalid option, please try again.");
					}
					break;
			}
		}
	}

	private boolean isSpecialCommand(String input) {
		return "4".equals(input);
	}

	private void performPurchase() {
		boolean purchasing = true;
		while (purchasing) {
			System.out.println("Current Money Provided: $" + String.format("%.2f", transaction.getBalance()));
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			System.out.println("Please select an option: ");

			String choice = scanner.nextLine();

			switch (choice) {
				case "1":
					feedMoney();
					break;
				case "2":
					selectProduct();
					break;
				case "3":
					purchasing = false;
					finishTransaction();
					break;
				default:
					System.out.println("Invalid option, please try again.");
					break;
			}
		}
	}


	private void feedMoney() {
		System.out.println("Please insert whole dollar amount: ");
		String amountStr = scanner.nextLine();
		try {
			int amount = Integer.parseInt(amountStr);
			if (amount > 0) {
				transaction.feedMoney(amount);
			} else {
				System.out.println("Invalid bill. Please use whole dollar amounts.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a whole dollar amount.");
		}
	}

	private void selectProduct() {
		System.out.println("Please enter the product code: ");
		String code = scanner.nextLine();
		Item item = inventory.getItem(code);
		if (item != null && item.isInStock()) {
			if (transaction.getBalance() >= item.getPrice()) {
				inventory.dispenseItem(code);
				transaction.purchaseItem(item, item.getPrice());
				System.out.println(item.getDispenseMessage());
			} else {
				System.out.println("Insufficient funds. Please feed more money.");
			}
		} else if (item == null) {
			System.out.println("Invalid product code. Please try again.");
		} else {
			System.out.println("Product is sold out.");
		}
	}

	private void finishTransaction() {
		double totalChange = transaction.getBalance();
		transaction.giveChange();
		System.out.println("Your change is: $" + String.format("%.2f", totalChange) + " (" + transaction.checkOut(totalChange) + ")");
		transaction.resetBalance();
	}

	public static void main(String[] args) {
		Application app = new Application();
		app.displayMainMenu();
	}

	private void displaySalesReport() {
		StringBuilder reportBuilder = new StringBuilder();
		String currentDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		double totalSales = 0.0;

		reportBuilder.append("Sales Report -").append(currentDateTime).append(System.lineSeparator()).append(System.lineSeparator());

		for (Map.Entry<String, Item> entry : inventory.getItems().entrySet()) {
			Item item = entry.getValue();
			double itemSales = (Item.MAX_STOCK - item.getStock()) * item.getPrice();
			totalSales += itemSales;

			reportBuilder.append(item.getName()).append("|").append(Item.MAX_STOCK - item.getStock()).append(System.lineSeparator());
		}

		reportBuilder.append(System.lineSeparator());
		reportBuilder.append("**TOTAL SALES** $").append(String.format("%.2f", totalSales)).append(System.lineSeparator());

		String report = reportBuilder.toString();
		System.out.println("Attempting to write sales report to file...");
		logger.writeSalesReport(report);
		System.out.println("Sales report should be written upon exiting.");
	}
}