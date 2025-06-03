package OOP.BankAccountProject;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
    private String username;
    private String pin;
    private double balance;
    private List<String> transactions;

    public BankAccount(String username, String pin){
        this.username = username;
        this.pin = pin;
        this.balance = 0;
        this.transactions = new ArrayList<>();
        log("Account created");
    }

    public boolean authenticate(String inputPin){
        return this.pin.equals(inputPin);
    }
    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            log("Deposited: $" + amount);
        }
    }

    public void withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            log("Withdraw: $" + amount);
        } else {
            log("Failed withdrawal attempt.");
        }
    }

    public void showDetails() {
        System.out.println("Username: " + username);
        System.out.println("Balance: " + balance);
    }

    public void showTransactions() {
        System.out.println("Transaction History: ");
        for(String t : transactions){
            System.out.println("- " + t);
        }
    }

    private void log(String action){
        transactions.add(action + " (Balance: $" + balance + ")");
    }

    public String getUsername() {
        return username;
    }
}
