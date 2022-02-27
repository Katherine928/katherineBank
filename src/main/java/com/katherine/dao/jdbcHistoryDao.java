package com.katherine.dao;

import com.katherine.model.Account;
import com.katherine.model.History;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class jdbcHistoryDao implements HistoryDao {


    private final JdbcTemplate jdbcTemplate;

    public jdbcHistoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<History> getHistoryById(int id) {
        List<History> histories = new ArrayList<>();
        String sql = "SELECT history_id, history_message, history_date, history_amount FROM history WHERE account_id = ? AND history_date >= ? -30";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id, LocalDate.now());
        while (result.next()) {
            histories.add(mapRowToHistory(result));
        }
        return histories;
    }

    @Override
    public void createHistory(int accountId, String message, LocalDate date, double moneyAmount) {
        String sql = "INSERT INTO history(account_id, history_message, history_date, history_amount) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,accountId,message,date,moneyAmount);
    }

    public History mapRowToHistory(SqlRowSet rowSet) {
        History history = new History();
        history.setHistoryId(rowSet.getInt("history_id"));
        history.setHistoryMessage(rowSet.getString("history_message"));
        history.setHistoryDate(rowSet.getDate("history_date").toLocalDate());
        history.setHistoryAmount(rowSet.getDouble("history_amount"));
        return history;
    }

}
