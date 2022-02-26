package com.katherine;

import com.katherine.dao.AccountDao;
import com.katherine.dao.jdbcAccountDao;
import com.katherine.model.Account;
import com.katherine.view.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class KatherineBankSystemCLI {

    private final UserInterface userInterface = new UserInterface(System.in);
    private final AccountDao accountDao;
    private final String LOGIN = "1";
    private final String CHECK_ACCOUNT_INFORMATION = "1";
    private final String CREATE_ACCOUNT = "2";
    private final String DEPOSIT = "2";
    private final String WITHDRAW = "3";
    private final String TRANSFER = "4";

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
                        switch (subMenuChoice) {
                            case CHECK_ACCOUNT_INFORMATION:
                                userInterface.displayAccountInformation(accountFind);
                                break;
                            case DEPOSIT:
                                double depositMoney = userInterface.getDepositAmountFromUser();
                                accountFind.depositMoneyToAccount(depositMoney);
                                userInterface.displayDepositSuccessMessage(depositMoney, accountFind.getBalance());
                                accountDao.updateAccountById(accountFind.getAccountId(), accountFind.getBalance());
                                break;
                            case WITHDRAW:
                                double moneyToWithdraw = userInterface.getWithdrawAmountFromUser();
                                if (accountFind.checkBalance(moneyToWithdraw)) {
                                    accountFind.withdrawMoneyFromAccount(moneyToWithdraw);
                                    userInterface.displayWithdrawSuccessMessage(moneyToWithdraw, accountFind.getBalance());
                                    accountDao.updateAccountById(accountFind.getAccountId(), accountFind.getBalance());
                                } else {
                                    userInterface.displayNotEnoughMoneyMessage();
                                }
                                break;
                            case TRANSFER:
                                int transferAccountId = userInterface.getTransferAccountId();
                                double transferAccountAmount = userInterface.getTransferAmount();
                                if (accountFind.transferMoney(transferAccountAmount)) {
                                    userInterface.displayTransferSuccessMessage(transferAccountAmount, accountFind.getBalance());
                                    accountDao.updateAccountById(accountFind.getAccountId(), accountFind.getBalance());
                                    accountDao.updateAccountById(transferAccountId, accountDao.getAccountByAccountId(transferAccountId).getBalance() + transferAccountAmount);
                                } else {
                                    userInterface.displayNotEnoughMoneyMessage();
                                }
                                break;
                            default:
                                userInterface.displayNotEnoughMoneyMessage();
                                break;
                        }
                        }

                } else {
                    userInterface.displayErrorMessage();
                }

            } else if (mainChoice.equals(CREATE_ACCOUNT)) {
                userInterface.displayGoodbyeMessage();
            } else {
                userInterface.displayErrorMessage();
                userInterface.displayGoodbyeMessage();
                running = false;
            }
        }

    }
}
