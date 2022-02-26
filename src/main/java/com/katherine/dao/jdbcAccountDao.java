package com.katherine.dao;

import com.katherine.model.Account;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.List;

public class jdbcAccountDao implements AccountDao{
    private final JdbcTemplate jdbcTemplate;

    public jdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Account getAccountByAccountId(int id){
        Account account = null;
        String sql = "SELECT account_id, account_first_name, account_last_name, account_user_name, balance FROM account WHERE account_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }


    @Override
    public int createAccount(String firstName, String lastName, String password, double balance){
        String sql = "INSERT INTO account(account_first_name, account_last_name, account_user_name, account_password, balance) \n" +
                "VALUES (?, ?, ?, ?, ?) RETURNING account_id";
       int id = jdbcTemplate.queryForObject(sql, Integer.class, firstName, lastName, "waitTOAssign", password, balance);
       return id;
    }

    @Override
    public Account getAccountByUserNameAndPassword(List<String> accountInformation) {
        Account account = null;
        String username = accountInformation.get(0).toUpperCase();
        String password = accountInformation.get(1);
        String sql = "SELECT account_id, account_first_name, account_last_name, account_user_name, balance FROM account WHERE account_user_name = ? AND account_password = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,username, password);
        if(result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }


    @Override
    public boolean checkAccount(List<String> accountInformation) {
        Account account = getAccountByUserNameAndPassword(accountInformation);
        return account != null;
    }



    @Override
    public void updateAccountById(int id, double newBalance) {
            String sql = "UPDATE account SET balance = ? WHERE account_id = ?";
            jdbcTemplate.update(sql, newBalance, id);
    }

    @Override
    public void updateUsernameById(int id, String newUserName) {
        String sql = "UPDATE account SET account_user_name = ? WHERE account_id = ?";
        jdbcTemplate.update(sql,newUserName, id);
    }

    @Override
    public void deleteAccountById(int id) {
        String sql = "DELETE FROM account WHERE account_id = ?";
        jdbcTemplate.update(sql, id);
    }


    public Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setAccountFirstName(rowSet.getString("account_first_name"));
        account.setAccountLastName(rowSet.getString("account_last_name"));
        account.setAccountUserName(rowSet.getString("account_user_name"));
        account.setBalance(rowSet.getDouble("balance"));
        return account;
    }
}
