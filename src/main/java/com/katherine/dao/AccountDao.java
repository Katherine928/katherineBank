package com.katherine.dao;

import com.katherine.model.Account;
import com.katherine.util.AccountNotFoundException;

import java.util.List;

public interface AccountDao {

    Account getAccountByAccountId(int id);

    int createAccount(String firstName, String lastName, String password, double balance);
    Account getAccountByUserNameAndPassword(List<String> accountInformation);
    void checkAccountByUserNameAndPassword(List<String> accountInformation) throws AccountNotFoundException;
    void updateAccountById(int id, double newBalance);
    void updateUsernameById(int id, String newUserName);
    void deleteAccountById(int id);
}
