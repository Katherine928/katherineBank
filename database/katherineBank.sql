DROP TABLE IF EXISTS account;
BEGIN TRANSACTION;

CREATE TABLE account (
  account_id serial,
	account_first_name varchar(99) NOT NULL,
	account_last_name varchar(99) NOT NULL,
	account_user_name varchar(99) NOT NULL,
	account_password varchar(99) NOT NULL,
	balance float NOT NULL,
  CONSTRAINT PK_account_account_id PRIMARY KEY (account_id)
);
commit
select * from account
INSERT INTO account (account_first_name, account_last_name, account_user_name, account_password, balance )
values('Katherine', 'Brooks', 'KBROOKS1', '123456', 1000)