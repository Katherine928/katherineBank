package com.katherine.model;

import java.util.List;
import java.util.Locale;

public class Account {
    private int accountId;
    private String accountFirstName;
    private String accountLastName;
    private String accountUserName;
    private String accountPassword;
    private double balance;

//    public Account(String accountFirstName, String accountLastName) {
//        this.accountFirstName = accountFirstName;
//        this.accountLastName = accountLastName;
//    }

    public boolean checkBalance(double money) {
        return money < balance;
    }
    public void depositMoneyToAccount(double money) {
        this.balance = this.balance + money;
    }
    public void withdrawMoneyFromAccount(double money) {
        this.balance = this.balance - money;
    }

    public boolean transferMoney(double money) {
        if(checkBalance(money)) {
            this.balance = this.balance - money;
            return true;
        } else {
            return false;
        }
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
