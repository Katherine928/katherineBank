package com.katherine.model;

import java.time.LocalDate;

public class History {


    private int historyId;
    private int accountId;
    private String historyMessage;
    private LocalDate historyDate;
    private double historyAmount;

    public History(int historyId,int accountId, String historyMessage, LocalDate historyDate, double historyAmount) {
        this.historyId = historyId;
        this.historyMessage = historyMessage;
        this.historyDate = historyDate;
        this.historyAmount = historyAmount;
    }

    public History() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getHistoryId() {
            return historyId;
        }

    public void setHistoryId(int historyId) {
            this.historyId = historyId;
        }

    public String getHistoryMessage() {
            return historyMessage;
        }

    public void setHistoryMessage(String historyMessage) {
            this.historyMessage = historyMessage;
        }

    public LocalDate getHistoryDate() {
            return historyDate;
        }

    public void setHistoryDate(LocalDate historyDate) {
            this.historyDate = historyDate;
        }

    public double getHistoryAmount() {
            return historyAmount;
        }

    public void setHistoryAmount(double historyAmount) {
            this.historyAmount = historyAmount;
        }


}
