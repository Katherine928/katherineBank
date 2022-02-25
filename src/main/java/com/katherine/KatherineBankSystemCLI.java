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
            if (mainChoice.equals("1")) {
                List<String> userInformation = userInterface.getLoginInformationFromUser();
                if(accountDao.checkAccount(userInformation)) {
                    userInterface.displayWelcomeMessage(accountDao.getAccountByUserNameAndPassword(userInformation).getAccountFirstName());
                    while (true) {
                        userInterface.displaySubMenu();
                        String subMenuChoice = userInterface.getChoiceFromUser();
                        if (subMenuChoice.equals("1")) {
                            userInterface.displayAccountInformation(accountDao.getAccountByUserNameAndPassword(userInformation));
                        }
                    }
                } else {
                    userInterface.displayErrorMessage();
                }

            } else if (mainChoice.equals("2")) {

            } else {
                userInterface.displayErrorMessage();
                userInterface.displayGoodbyeMessage();
                running = false;
            }
        }

    }
}
