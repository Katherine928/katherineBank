package com.katherine.dao;

import com.katherine.model.Account;

import java.util.List;

public interface AccountDao {

    Account getAccountByAccountId(int id);

    Account createAccount(Account newAccount);
    Account getAccountByUserNameAndPassword(List<String> accountInformation);
    boolean checkAccount(List<String> accountInformation);
    void updateAccountById(int id, double newBalance);
}
