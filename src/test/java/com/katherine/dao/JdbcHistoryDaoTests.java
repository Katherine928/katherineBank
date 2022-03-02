package com.katherine.dao;

import com.katherine.model.History;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcHistoryDaoTests extends BaseDaoTests{

    private static final History HISTORY_1 = new History(1,1,"DEPOSIT",LocalDate.of(2022,1,12),50);
    private static final History HISTORY_2 = new History(2,1,"DEPOSIT",LocalDate.of(2022,2,13),50);
    private static final History HISTORY_3 = new History(3,1,"TRANSFER",LocalDate.of(2022,2,14),50);
    private static final History HISTORY_4 = new History(4,2,"WITHDRAW",LocalDate.of(2020,2,12),50);

    private JdbcHistoryDao sut;
    private History testHistory;

    @Before
    public void setup() {
        sut = new JdbcHistoryDao(dataSource);
        testHistory = new History(6,2,"DEPOSIT",LocalDate.now(),100);
    }

    @Test
    public void getHistoryByIdTest() {
        List<History> testCase = sut.getHistoryById(1);
        Assert.assertEquals(2, testCase.size());
        assertHistoryMatch(HISTORY_2, testCase.get(0));
        assertHistoryMatch(HISTORY_3, testCase.get(1));
    }

    @Test
    public void createHistoryTest() {
        sut.createHistory(2,"DEPOSIT",LocalDate.now(),100);
        List<History> histories = sut.getHistoryById(2);
        Assert.assertEquals(2,histories.size());
        assertHistoryMatch(testHistory, histories.get(1));
    }


    private void assertHistoryMatch(History expected, History actual) {
        Assert.assertEquals(expected.getHistoryId(), actual.getHistoryId());
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getHistoryMessage(), actual.getHistoryMessage());
        Assert.assertEquals(expected.getHistoryDate(), actual.getHistoryDate());
        Assert.assertEquals(expected.getHistoryAmount(),actual.getHistoryAmount(),0.1);
    }
}
