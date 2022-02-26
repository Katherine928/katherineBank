package com.katherine.dao;

import com.katherine.model.Account;

import java.util.List;

public interface AccountDao {

    Account getAccountByAccountId(int id);

    int createAccount(String firstName, String lastName, String password, double balance);
    Account getAccountByUserNameAndPassword(List<String> accountInformation);
    boolean checkAccount(List<String> accountInformation);
    void updateAccountById(int id, double newBalance);
    void updateUsernameById(int id, String newUserName);
    void deleteAccountById(int id);
}
