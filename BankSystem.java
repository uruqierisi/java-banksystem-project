package OOP.BankAccountProject;

import java.io.*;
import java.util.HashMap;


public class BankSystem {
    private static final String FILE_NAME = "accounts.db";
    private HashMap<String, BankAccount> accounts;

    public BankSystem() {
        accounts = loadAccounts();
    }

    public boolean register(String username, String pin) {
        if (accounts.containsKey(username)) return false;
        accounts.put(username, new BankAccount(username, pin));
        saveAccounts();
        return true;
    }

    public BankAccount login(String username, String pin) {
        BankAccount acc = accounts.get(username);
        if (acc != null && acc.authenticate(pin)) {
            return acc;
        }
        return null;
    }

    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Failed to save accounts. ");
        }
    }

    @SuppressWarnings("unchecked")
    private HashMap<String, BankAccount> loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (HashMap<String, BankAccount>) ois.readObject();
        } catch (IOException | ClassNotFoundException e ) {
            return new HashMap<>();
        }
    }
}