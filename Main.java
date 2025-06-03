package OOP.BankAccountProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankSystem bank = new BankSystem();
        BankAccount currentUser = null;

        while(true) {
            System.out.println("\n---- Welcome to Raiffaisen Bank ----");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("New username: ");
                String username = scanner.nextLine();
                System.out.print("Choose a 4-digit PIN: ");
                String pin = scanner.nextLine();

                if(bank.register(username, pin)) {
                    System.out.println("Account created Successfully!");
                } else {
                    System.out.println("username already exists!");
                }
            } else if (option == 2) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("PIN: ");
                String pin = scanner.nextLine();

                currentUser = bank.login(username, pin);
                if (currentUser != null) {
                    System.out.println("Account logged in successfully!");
                    userMenu(scanner, currentUser, bank);
                } else {
                    System.out.println("Invalid username or PIN!");
                }
            } else if (option == 3) {
                System.out.println("Thank you for using Raiffaisen Bank!");
                break;
            } else {
                System.out.println("Invalid option!");
            }
        }

    }

    private static void userMenu(Scanner scanner, BankAccount account, BankSystem bank) {
        int choice;
        do {
            System.out.println("\n---- User Menu ----");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transactions");
            System.out.println("5. Logout");
            System.out.print("Choose: ");
            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    account.showDetails();
                    break;
                case 2:
                    System.out.print("Amount to deposit: ");
                    double dep = scanner.nextDouble();
                    account.deposit(dep);
                    bank.saveAccounts();
                    break;
                case 3:
                    System.out.print("Amount to withdraw: ");
                    double wd = scanner.nextDouble();
                    account.withdraw(wd);
                    bank.saveAccounts();
                    break;
                case 4:
                    account.showTransactions();
                    break;
                case 5:
                    System.out.println("Thank you for using Raiffaisen Bank!");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        } while (choice !=5);
    }
}
