package com.katherine.view;

import com.katherine.model.Account;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner myScanner;

    public UserInterface(InputStream input) {
        this.myScanner = new Scanner(input);
    }

    public void displayBankNameAndWelcomeMessage() {
        System.out.println("/***");
        System.out.println(" *      _  __     _   _            _            ___            _  ");
        System.out.println(" *     | |/ /__ _| |_| |_  ___ _ _(_)_ _  ___  | _ ) __ _ _ _ | |__");
        System.out.println(" *     | ' </ _` |  _| ' \\/ -_) '_| | ' \\/ -_) | _ \\/ _` | ' \\| / /");
        System.out.println(" *     |_|\\_\\__,_|\\__|_||_\\___|_| |_|_||_\\___| |___/\\__,_|_||_|_\\_\\");
        System.out.println(" *                                                                 ");
        System.out.println(" */");
        System.out.println("\nWelcome to Katherine Bank!");
        System.out.println("* * * * * * * * * * *\n");
        System.out.println("Hi, I am Katherine 💁 Your virtual Customer Service!");
        System.out.println("What can I do for you today?\n");
    }

    public String getChoiceFromUser() {
        System.out.println("Please choose from above...");
        return myScanner.nextLine();
    }
    public void displayMainMenu() {
        System.out.println("1) Login");
        System.out.println("2) Create new account\n");
    }
    public void displayErrorMessage() {
        System.out.println("Error 💥: The information you just entered is not valid, please come back and try again!\n");
    }
    public void displayGoodbyeMessage() {
        System.out.println("Thanks for choose Katherine Bank! Have a great Day ❤");
        System.out.println("/***");
        System.out.println(" *      _____ _              _    __   __        ");
        System.out.println(" *     |_   _| |_  __ _ _ _ | |__ \\ \\ / /__ _  _ ");
        System.out.println(" *       | | | ' \\/ _` | ' \\| / /  \\ V / _ \\ || |");
        System.out.println(" *       |_| |_||_\\__,_|_||_|_\\_\\   |_|\\___/\\_,_|");
        System.out.println(" * ");
        System.out.println(" */");
    }
    public List<String> getLoginInformationFromUser() {
        List<String> loginInformation= new ArrayList<>();
        System.out.println("Please enter your username:");
        String username = myScanner.nextLine();
        System.out.println("Please enter your password:");
        String password = myScanner.nextLine();
        loginInformation.add(username);
        loginInformation.add(password);
        return loginInformation;
    }

    public void displayWelcomeMessage(String name) {
        System.out.println("\nWelcome back, " + name + "!");
        System.out.println("* * * * * * * * * * * * * * * * *\n");;
    }

    public void displaySubMenu() {
        System.out.println("1) Check account information");
        System.out.println("2) Deposit");
        System.out.println("3) Withdraw");
        System.out.println("4) Transfer");
    }

    public void displayAccountInformation(Account account) {
        System.out.println("---------------------------------------------------------------------------");
        String accountInformation = String.format("%-20s %-20s %-20s $%-20s", account.getAccountId(), account.getAccountFirstName(), account.getAccountLastName(), account.getBalance());
        String banner = String.format("%-20s %-20s %-20s %-20s","Account Number", "First Name", "Last Name", "Balance");
        System.out.println(banner);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(accountInformation + "\n\n");
    }

    public double getDepositAmountFromUser() {
        System.out.println("How much do you want to deposit?");
        double depositMoney = Double.parseDouble(myScanner.nextLine());
        return depositMoney;
    }

    public double getWithdrawAmountFromUser() {
        System.out.println("How much do you want to withdraw?");
        double withdrawMoney = Double.parseDouble(myScanner.nextLine());
        return withdrawMoney;
    }

    public void displayDepositSuccessMessage(double moneyToDeposit, double balance) {
        System.out.println("\n🥳 $" + moneyToDeposit + " is successfully deposited in your account, your current balance is: $" + balance);
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");;
    }

    public void displayWithdrawSuccessMessage(double moneyToWithdraw, double balance) {
        System.out.println("\n🥳 $" + moneyToWithdraw + " is successfully withdraw from your account, your current balance is: $" + balance);
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");;
    }

    public void displayNotEnoughMoneyMessage() {
        System.out.println("\n❌ Sorry, there is not enough money in the current account!");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");;
    }

    public void displayTransferSuccessMessage(double moneyToTransfer, double balance) {
        System.out.println("\n🥳 $" + moneyToTransfer + " is successfully withdraw from your account, your current balance is: $" + balance);
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");;
    }

    public int getTransferAccountId() {

        System.out.println("What is the transfer account number?");
        return Integer.parseInt(myScanner.nextLine());
    }
    public double getTransferAmount() {
        System.out.println("What is the transfer amount?");
        return Double.parseDouble(myScanner.nextLine());
    }
}
