
/**
* CustomerInterface.java
* @author Gerardo R. Padilla Jr. 
* @author Nhan Ha
* CIS 22C Lab 5
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Scanner;

public class CustomerInterface {
	public static void main(String[] args) throws FileNotFoundException {

		// Declaring variables
		CustomerInterface ci = new CustomerInterface();
		BST<MutualFundAccount> account_value = new BST<>();
		BST<MutualFundAccount> account_name = new BST<>();
		List<MutualFund> funds = new List<>();
		Scanner input = null;

		String mutualName, ticker, choice = "Z";

		double sharePrice;

		File file = new File("mutual_funds.txt");
		input = new Scanner(file);

		// adding funds from file to a MutualFund
		while (input.hasNext() == true) {
			mutualName = input.nextLine();
			ticker = input.nextLine();
			sharePrice = input.nextDouble();
			input.nextLine();
			MutualFund monke = new MutualFund(mutualName, ticker, sharePrice);

			funds.addLast(monke);

		}
		input.close();

		System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");
		input = new Scanner(System.in);

		do {
			System.out.print("Please select from the following options:\n\n" + "	A. Purchase a Fund\n"
					+ "	B. Sell a Fund\n" + "	C. Display Your Current Funds\n" + "	X. Exit\n\n");
			System.out.print("Enter your choice: ");

			// Converting user input to uppercase and checking for each case and calls a
			// different method.
			if (input.hasNextLine()) {
				choice = input.nextLine();
				choice = choice.toUpperCase();
			}

			switch (choice) {

			case "A":
				ci.purchaseFunds(input, funds, account_value, account_name);
				break;
			case "B":
				ci.sellFunds(input, funds, account_value, account_name);
				break;
			case "C":
				ci.ShowFunds(input, account_value, account_name, funds);
				break;
			case "X":
				System.out.println("\nGoodbye!");
				return;
			default:
				System.out.println("Invalid menu option. Please enter A-C or X to exit.\n");
				break;
			}

		} while (true);

	}

	private void purchaseFunds(Scanner input, List<MutualFund> funds, BST<MutualFundAccount> account_amount,
			BST<MutualFundAccount> account_name) {
		int choice = -1;
		MutualFund fundToSearch = null;
		System.out.println("\nPlease select from the options below: \n");
		funds.printNumberedList();
		System.out.print("Enter your choice: (1-7): ");
		if (input.hasNextInt()) {
			choice = input.nextInt();
			System.out.println();
		}
		input.nextLine();
		if (choice < 1 || choice > 7) {
			System.out.println("Invalid Choice!");
			System.out.println();
			return;
		}

		funds.iteratorToIndex(choice - 1);
		fundToSearch = funds.getIterator();
		MutualFundAccount account = new MutualFundAccount(fundToSearch);
		MutualFundAccount accountToUpdate = null;

		System.out.print("Enter the number of shares to purchase: ");
		if (input.hasNextInt()) {
			choice = input.nextInt();
			input.nextLine();

		} else {
			System.out.println("Invalid Amount of Shares!");
			input.nextLine();
			return;
		}
		System.out.println();

		if ((account_name.search(account, new NameComparator())) != null) {
			accountToUpdate = account_name.search(account, new NameComparator());
			account.updateShares(accountToUpdate.getNumShares());
			account_amount.remove(account, new ValueComparator());
			account_name.remove(account, new NameComparator());
			accountToUpdate.updateShares(choice);
			account_amount.insert(accountToUpdate, new ValueComparator());
			account_name.insert(accountToUpdate, new NameComparator());
		} else {
			accountToUpdate = new MutualFundAccount(funds.getIterator());
			accountToUpdate.updateShares(choice);
			account_amount.insert(accountToUpdate, new ValueComparator());
			account_name.insert(accountToUpdate, new NameComparator());
		}
	}

	private void sellFunds(Scanner input, List<MutualFund> funds, BST<MutualFundAccount> account_amount,
			BST<MutualFundAccount> account_name) {
		String choice = "";
		double shares = 0;
		MutualFundAccount accountToUpdate = null;
		if (account_name.isEmpty()) {
			System.out.println();
			System.out.println("You don't have any funds to sell at this time.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("You own the following mutual funds: ");
			account_name.inOrderPrint();
			System.out.print("Enter the name of the fund to sell: ");
			if (input.hasNextLine()) {
				choice = input.nextLine();
				choice = choice.trim();
			}
			MutualFundAccount choiceFund = new MutualFundAccount(new MutualFund(choice));

			if ((accountToUpdate = account_name.search(choiceFund, new NameComparator())) != null) {

				System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");
				if (input.hasNextLine()) {
					choice = input.nextLine();
					choice = choice.trim();
					try {
						if (choice.toUpperCase().equals("ALL")) {
							shares = accountToUpdate.getNumShares();
						} else if ((shares = Double.parseDouble(choice)) > 0
								&& shares <= accountToUpdate.getNumShares()) {
							// assignment is done in if statement
						} else {
							System.out.println("Invalid Amount of Shares!");
							// input.nextLine();
							return;
						}

					} catch (Exception NumberFormatException) {
						System.out.println("Invalid Amount of Shares!");
						return;
					}
				} else {
					System.out.println("Invalid Amount of Shares!");
					// input.nextLine();
					return;
				}
				System.out.println();
				shares = shares * -1;

				choiceFund.updateShares(accountToUpdate.getNumShares());
				choiceFund.getMf().setPricePerShare(accountToUpdate.getMf().getPricePerShare());
				account_amount.remove(choiceFund, new ValueComparator());
				account_name.remove(choiceFund, new NameComparator());
				accountToUpdate.updateShares(shares);

				if (accountToUpdate.getNumShares() != 0) {
					account_amount.insert(accountToUpdate, new ValueComparator());
					account_name.insert(accountToUpdate, new NameComparator());
				}

			} else {
				System.out.println("Fund not found");
				return;
			}
		}
	}

	private <T> void ShowFunds(Scanner input, BST<T> value, BST<T> name, List<MutualFund> funds) {
		if (name.isEmpty()) {
			System.out.println();
			System.out.println("You don't have any funds to display at this time.");
			System.out.println();
		} else {
			funds.toString();
			System.out.println("\nView Your Mutual Funds By: \n\n\t1. Name\n\t2. Value\n");
			System.out.print("Enter your choice (1 or 2): ");
			// Scanner input = new Scanner(System.in);
			String choice = "";

			if (input.hasNext()) {
				choice = input.nextLine().toUpperCase();
				System.out.println("");
			}

			if (choice.equals("1")) {
				name.inOrderPrint();
			} else if (choice.equals("2")) {
				value.inOrderPrint();
			} else {

				System.out.println("Invalid Choice!\n");
			}
			return;
		}
	}
}
