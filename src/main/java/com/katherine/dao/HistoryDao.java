package com.katherine.dao;

import com.katherine.model.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryDao {
    List<History> getHistoryById(int id);
    void createHistory(int accountId, String message, LocalDate date, double moneyAmount);
}
