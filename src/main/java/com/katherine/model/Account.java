package com.katherine.model;

import com.katherine.util.InvalidInputException;
import com.katherine.util.NotEnoughBalanceException;

import java.util.List;
import java.util.Locale;

public class Account {
    private int accountId;
    private String accountFirstName;
    private String accountLastName;
    private String accountUserName;
    private String accountPassword;
    private double balance;

    public Account(int id, String accountFirstName, String accountLastName, String accountPassword) {
        this.accountId = id;
        this.accountFirstName = accountFirstName;
        this.accountLastName = accountLastName;
        this.accountPassword = accountPassword;
        this.balance = 0;
    }
    public  Account() {
    }

    public Account(int accountId, String accountFirstName, String accountLastName, String accountUserName, String accountPassword, double balance) {
        this.accountId = accountId;
        this.accountFirstName = accountFirstName;
        this.accountLastName = accountLastName;
        this.accountUserName = accountUserName;
        this.accountPassword = accountPassword;
        this.balance = balance;
    }

    public void checkBalance(double money) throws NotEnoughBalanceException {
        if (money > balance) {
            throw new NotEnoughBalanceException();
        }
    }
    public void checkMoneyInput(double money) throws InvalidInputException {
        if (money < 0) {
            throw new InvalidInputException();
        }
    }
    public void depositMoneyToAccount(double money) {
        this.balance = this.balance + money;
    }
    public void withdrawMoneyFromAccount(double money) {
        this.balance = this.balance - money;
    }

    public void transferMoney(double money) {

        this.balance = this.balance - money;

    }
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountFirstName() {
        return accountFirstName;
    }

    public void setAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
    }

    public String getAccountLastName() {
        return accountLastName;
    }

    public void setAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
    }

    public String getAccountUserName() {
        return (accountFirstName.substring(0,1) + accountLastName + accountId).toUpperCase();
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
