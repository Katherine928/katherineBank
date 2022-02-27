DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS history;

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
)

INSERT INTO account (account_first_name, account_last_name, account_user_name, account_password, balance )
values('Katherine', 'Brooks', 'KBROOKS1', '123456', 1000);
INSERT INTO account (account_first_name, account_last_name, account_user_name, account_password, balance )
values('Lilian', 'Smith', 'LSMITH2', '123abc', 500);
