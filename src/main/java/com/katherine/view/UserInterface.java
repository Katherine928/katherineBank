package com.katherine.view;

import com.katherine.model.Account;
import com.katherine.model.History;
import com.katherine.util.InvalidInputException;

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
        System.out.println("Hi, I am Katherine 🥰 Your virtual Customer Service!");
        System.out.println("What can I do for you today?\n");
    }

    public String getChoiceFromUser() {
        System.out.println("Please choose from above...");
        return myScanner.nextLine();
    }
    public void displayMainMenu() {
        System.out.println("1) Login");
        System.out.println("2) Create new account");
        System.out.println("3) Quit\n");
    }
    public void displayErrorMessage() {
        System.out.println("Error 💥: The information you just entered is not valid, please come back and try again!\n");
    }
    public void displayGoodbyeMessage() {
        System.out.println("Thanks for choosing Katherine Bank! Have a great Day ❤");
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
        System.out.println("1) Check Account Information");
        System.out.println("2) Deposit");
        System.out.println("3) Withdraw");
        System.out.println("4) Transfer");
        System.out.println("5) Delete Account");
        System.out.println("6) log Out");
    }

    public void displayAccountInformation(Account account) {
        System.out.println("---------------------------------------------------------------------------");
        String accountInformation = String.format("%-20s %-20s %-20s $%-20s", account.getAccountId(), account.getAccountFirstName(), account.getAccountLastName(), account.getBalance());
        String banner = String.format("%-20s %-20s %-20s %-20s","Account Number", "First Name", "Last Name", "Balance");
        System.out.println(banner);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(accountInformation + "\n\n");
    }

    public double getDepositAmountFromUser(){
        System.out.println("How much do you want to deposit?");
        double depositMoney;
        depositMoney = Double.parseDouble(myScanner.nextLine());
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
        System.out.println("\n‼️ Sorry, there is not enough money in the current account!");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");;
    }

    public void displayAccountDeleteMessage() {
        System.out.println("\n✔️ Your account has been successfully deleted. We will miss you!");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    }

    public void displayTransferSuccessMessage(double moneyToTransfer, double balance, int accountId, String name) {
        System.out.println("\n🥳   " + name + " received your $" + moneyToTransfer + "!");
        System.out.println("✔️ $" + moneyToTransfer + " is successfully transferred to account #" + accountId + ", your current balance is: $" + balance);
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");;
    }

    public void displayCreateAccountSuccessMessage(Account newAccount) {
        System.out.println("\n🥳 Account have been successfully created. Here is your account number and account username\n");
        System.out.println("---------------------------------------------------------------------------");
        String successfulMessageFormat = String.format("%-20s %-20s", "Account#", "Username");
        System.out.println(successfulMessageFormat);
        System.out.println("---------------------------------------------------------------------------");
        String successfulMessage = String.format("%-20s %-20s", newAccount.getAccountId(), newAccount.getAccountUserName()+"\n");
        System.out.println(successfulMessage);
    }

    public int getTransferAccountId() {

        System.out.println("What is the transfer account number?");
        return Integer.parseInt(myScanner.nextLine());
    }
    public List<String> getTransferName() {
        List<String> transferAccountName = new ArrayList<>();
        System.out.println("What is the fist name for the account?");
        String firstName = myScanner.nextLine();
        System.out.println("What is the last name for the account?");
        String lastName = myScanner.nextLine();
        transferAccountName.add(firstName);
        transferAccountName.add(lastName);
        return transferAccountName;
    }
    public double getTransferAmount() {
        System.out.println("What is the transfer amount?");
        return Double.parseDouble(myScanner.nextLine());
    }
    public void displayWrongLoginMessage() {
        System.out.println("\n‼️ Wrong username Or password");
        System.out.println("* * * * * * * * * * * * * * * * * * *\n");;
    }

    public void displayAccountNotFoundErrorMessage() {
        System.out.println("\n‼️ Sorry! We didn't find the account, please check the account number or account name!");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");;
    }

    public List<String> getNewAccountInformation() {
        List<String> newAccountInfo = new ArrayList<>();
        System.out.println("What is your first name?");
        String first_name = myScanner.nextLine();
        first_name = first_name.substring(0,1).toUpperCase() + first_name.substring(1).toLowerCase();
        System.out.println("What is your last name?");
        String last_name = myScanner.nextLine();
        last_name = last_name.substring(0,1).toUpperCase() + last_name.substring(1).toLowerCase();
        System.out.println("What is your password?");
        String password = myScanner.nextLine();
        System.out.println("Please Confirm your password:");
        String passwordConfirm = myScanner.nextLine();
        System.out.println("How much money do you want to put in the account?");
        String startBalance = myScanner.nextLine();
        if (password.equals(passwordConfirm)) {
            newAccountInfo.add(first_name);
            newAccountInfo.add(last_name);
            newAccountInfo.add(password);
            newAccountInfo.add(startBalance);
        }
        return newAccountInfo;
    }

    public String askForHistory() {
        System.out.println("\nShow 30 day Transaction History? (Y/N)\n");
        return myScanner.nextLine().toUpperCase();
    }

    public void displayTransactionHistory(List<History> histories) {
        System.out.println("---------------------------------------------------------------------------");
        String historyBanner = String.format("%-20s %-20s %-20s", "Date", "Message","Amount");
        System.out.println(historyBanner);
        System.out.println("---------------------------------------------------------------------------");
        for(History history: histories) {
            String historyFormat = String.format("%-20s %-20s $%-20s",history.getHistoryDate(),history.getHistoryMessage(),history.getHistoryAmount());
            System.out.println(historyFormat + "\n");
        }
        System.out.println("\n");
    }

    public void displayInvalidInputMessage() {
        System.out.println("\n ‼️Invalid Money Input!\n");
    }
}
