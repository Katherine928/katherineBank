package com.katherine;

import com.katherine.dao.AccountDao;
import com.katherine.dao.HistoryDao;
import com.katherine.dao.jdbcAccountDao;
import com.katherine.dao.jdbcHistoryDao;
import com.katherine.model.Account;
import com.katherine.model.History;
import com.katherine.view.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class KatherineBankSystemCLI {

    private final UserInterface userInterface = new UserInterface(System.in);
    private final AccountDao accountDao;
    private final HistoryDao historyDao;
    private final String LOGIN = "1";
    private final String CHECK_ACCOUNT_INFORMATION = "1";
    private final String CREATE_ACCOUNT = "2";
    private final String DEPOSIT = "2";
    private final String WITHDRAW = "3";
    private final String QUIT = "3";
    private final String TRANSFER = "4";
    private final String DELETE_ACCOUNT = "5";
    private final String LOGOUT = "6";

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/KatherineBank");
        dataSource.setUsername("postgres");
        dataSource.setPassword("Postgres1");
        KatherineBankSystemCLI cli = new KatherineBankSystemCLI(dataSource);
        cli.run();
    }

    public KatherineBankSystemCLI(DataSource dataSource) {
        accountDao = new jdbcAccountDao(dataSource);
        historyDao = new jdbcHistoryDao(dataSource);

    }

    public void run() {
        userInterface.displayBankNameAndWelcomeMessage();
        boolean running = true;
        while (running) {
            userInterface.displayMainMenu();
            String mainChoice = userInterface.getChoiceFromUser();
            if (mainChoice.equals(LOGIN)) {
                List<String> userInformation = userInterface.getLoginInformationFromUser();
                if(accountDao.checkAccount(userInformation)) {
                    Account accountFind = accountDao.getAccountByUserNameAndPassword(userInformation);
                    userInterface.displayWelcomeMessage(accountFind.getAccountFirstName());
                    while (true) {
                        userInterface.displaySubMenu();
                        String subMenuChoice = userInterface.getChoiceFromUser();
                        if (subMenuChoice.equals(CHECK_ACCOUNT_INFORMATION)) {
                            userInterface.displayAccountInformation(accountFind);
                            String showHistory = userInterface.askForHistory();
                            if(showHistory.equals("Y")) {
                                List<History> histories = historyDao.getHistoryById(accountFind.getAccountId());
                                userInterface.displayTransactionHistory(histories);
                            }
                        } else if (subMenuChoice.equals(DEPOSIT)) {
                            double depositMoney = userInterface.getDepositAmountFromUser();
                            accountFind.depositMoneyToAccount(depositMoney);
                            userInterface.displayDepositSuccessMessage(depositMoney, accountFind.getBalance());
                            accountDao.updateAccountById(accountFind.getAccountId(), accountFind.getBalance());
                            historyDao.createHistory(accountFind.getAccountId(),"DEPOSIT", LocalDate.now(),depositMoney);
                        } else if (subMenuChoice.equals(WITHDRAW)) {
                            double moneyToWithdraw = userInterface.getWithdrawAmountFromUser();
                            if (accountFind.checkBalance(moneyToWithdraw)) {
                                accountFind.withdrawMoneyFromAccount(moneyToWithdraw);
                                userInterface.displayWithdrawSuccessMessage(moneyToWithdraw, accountFind.getBalance());
                                accountDao.updateAccountById(accountFind.getAccountId(), accountFind.getBalance());
                                historyDao.createHistory(accountFind.getAccountId(),"WITHDRAW", LocalDate.now(),moneyToWithdraw);
                            }
                        } else if (subMenuChoice.equals(TRANSFER)) {
                            int transferAccountId = userInterface.getTransferAccountId();
                            List<String> transferAccountName = userInterface.getTransferName();
                            Account accountTransfer = accountDao.getAccountByAccountId(transferAccountId);
                            if (accountTransfer == null || !accountTransfer.getAccountFirstName().equalsIgnoreCase(transferAccountName.get(0)) || !accountTransfer.getAccountLastName().equalsIgnoreCase(transferAccountName.get(1))) {
                                userInterface.displayAccountNotFoundErrorMessage();
                            } else {
                                double transferAccountAmount = userInterface.getTransferAmount();
                                if (accountFind.transferMoney(transferAccountAmount)) {
                                    accountDao.updateAccountById(accountFind.getAccountId(), accountFind.getBalance());
                                    accountDao.updateAccountById(transferAccountId, accountDao.getAccountByAccountId(transferAccountId).getBalance() + transferAccountAmount);
                                    userInterface.displayTransferSuccessMessage(transferAccountAmount, accountFind.getBalance(), transferAccountId, transferAccountName.get(0).toUpperCase());
                                    historyDao.createHistory(accountFind.getAccountId(),"TRANSFER", LocalDate.now(),transferAccountAmount);
                                    historyDao.createHistory(transferAccountId,"RECEIVE", LocalDate.now(),transferAccountAmount);
                                } else {
                                    userInterface.displayNotEnoughMoneyMessage();
                                }
                            }
                        } else if (subMenuChoice.equals(DELETE_ACCOUNT)) {
                            accountDao.deleteAccountById(accountFind.getAccountId());
                            userInterface.displayAccountDeleteMessage();
                            break;
                        }
                        else if (subMenuChoice.equals(LOGOUT)) {
                            break;
                        } else {
                            userInterface.displayErrorMessage();
                        }
                    }
                } else {
                    userInterface.displayWrongLoginMessage();
                }
            } else if (mainChoice.equals(CREATE_ACCOUNT)) {
                List<String> accountInfo = userInterface.getNewAccountInformation();
                if (accountInfo != null) {
                    int newAccountId = accountDao.createAccount(accountInfo.get(0), accountInfo.get(1), accountInfo.get(2), Double.parseDouble(accountInfo.get(3)));
                    Account newAccount = new Account(newAccountId, accountInfo.get(0),accountInfo.get(1), accountInfo.get(2));
                    accountDao.updateUsernameById(newAccountId, newAccount.getAccountUserName());
                    userInterface.displayCreateAccountSuccessMessage(newAccount);
                } else {
                    System.out.println("Password and confirm password does not match!");
                }
            } else if (mainChoice.equals(QUIT)) {
                userInterface.displayGoodbyeMessage();
                running = false;
            }
            else {
                userInterface.displayErrorMessage();
                userInterface.displayGoodbyeMessage();
                running = false;
            }
        }

    }
}
