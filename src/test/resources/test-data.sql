BEGIN TRANSACTION;

DROP TABLE IF EXISTS account, history CASCADE;

CREATE TABLE account (
    account_id serial,
	account_first_name varchar(99) NOT NULL,
	account_last_name varchar(99) NOT NULL,
	account_user_name varchar(99) NOT NULL,
	account_password varchar(99) NOT NULL,
	balance float NOT NULL,
  CONSTRAINT PK_account_account_id PRIMARY KEY (account_id)
);

CREATE TABLE history (
	history_id serial NOT NULL,
	account_id int NOT NULL,
	history_message varchar(99) NOT NULL,
	history_date date NOT NULL,
	history_amount float NOT NULL,
	CONSTRAINT PK_history_history_id PRIMARY KEY (history_id),
	CONSTRAINT FK_history_account_id FOREIGN KEY(account_id) REFERENCES account(account_id)
);

INSERT INTO account (account_first_name, account_last_name, account_user_name, account_password, balance )
VALUES('FirstName1', 'LastName1', 'FLASTNAME11', '1234', 1000);
INSERT INTO account (account_first_name, account_last_name, account_user_name, account_password, balance )
VALUES('FirstName2', 'LastName2', 'FLASTNAME22', '2345', 500);

INSERT INTO history(account_id, history_message, history_date, history_amount)
VALUES(1,'DEPOSIT','2022-01-12',50);
INSERT INTO history(account_id, history_message, history_date, history_amount)
VALUES(1,'DEPOSIT','2022-02-13',50);
INSERT INTO history(account_id, history_message, history_date, history_amount)
VALUES(1,'TRANSFER','2022-02-14',50);

INSERT INTO history(account_id, history_message, history_date, history_amount)
VALUES(2,'WITHDRAW','2021-02-12',50);
INSERT INTO history(account_id, history_message, history_date, history_amount)
VALUES(2,'RECEIVE','2022-02-12',50);

COMMIT;