package com.katherine.dao;

import com.katherine.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JdbcAccountDaoTests extends BaseDaoTests{

    private static final Account ACCOUNT_1 = new Account(1,"FirstName1","LastName1","FLASTNAME11","1234",1000);
    private static final Account ACCOUNT_2 = new Account(2,"FirstName2","LastName2","FLASTNAME22","2345",500);

    private JdbcAccountDao sut;
    private Account testAccount;

    @Before
    public void setup() {
        sut = new JdbcAccountDao(dataSource);
        testAccount = new Account(3, "FirstName3","LastName3","FLASTNAME33","3456", 800);
    }

    @Test
    public void getAccountByAccountIdTest() {
        Account testCase = sut.getAccountByAccountId(1);
        assertAccountMatch(ACCOUNT_1, testCase);

    }

    @Test
    public void createAccount_return_accountId_Test() {
        int createId = sut.createAccount("FirstName3","LastName3","3456",800);
        Account createdAccount = sut.getAccountByAccountId(createId);
        Assert.assertTrue(createId > 0);
        assertAccountMatch(testAccount, createdAccount);

    }

    @Test
    public void getAccountByUserNameAndPasswordTest() {
        List<String> testAccountInformation = Arrays.asList("FLASTNAME11","1234");
        Account testCase = sut.getAccountByUserNameAndPassword(testAccountInformation);
        assertAccountMatch(ACCOUNT_1,testCase);
    }

    @Test
    public void updateAccountByIdTest() {
        sut.updateAccountById(2,800);
        ACCOUNT_2.setBalance(800);
        assertAccountMatch(ACCOUNT_2,sut.getAccountByAccountId(2));

    }

    @Test
    public void deleteAccountByIdTest() {
        sut.deleteAccountById(2);
        Account testCase = sut.getAccountByAccountId(2);
        Assert.assertNull(testCase);
    }


    private void assertAccountMatch(Account expected, Account actual) {
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getAccountFirstName(), actual.getAccountFirstName());
        Assert.assertEquals(expected.getAccountLastName(), actual.getAccountLastName());
        Assert.assertEquals(expected.getAccountPassword(), actual.getAccountPassword());
        Assert.assertEquals(expected.getAccountUserName(), actual.getAccountUserName());
        Assert.assertEquals(expected.getBalance(), actual.getBalance(),0.1);
    }

}
