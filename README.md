This repo is an Object-Oriented implementation of an online bank done by Vasily Ilin and Adam Streich as part of the Spring 2020 iteration of CS591, Object-Oriented Design, at Boston University. Go to Bank Design Document.pdf for a detailed description of the project.

Below is the complete list of classes used in the Bank. We give brief comments about some of them, and indicate inheritance structure with tabs.
Core classes:
Main.java
Bank.java -- holds static pointers to manager, customers, current date and the stock market
Account.java
	CheckingAccount.java
	ManagerAccount.java
	SavingsAccount.java
SecuritiesAccount.java
Loan.java -- a loan is a type of account. It can be transferred and deposited to.
	PendingLoan.java -- pending loan. Becomes a loan if approved.
User.java -- user of the bank
Customer.java
	Manager.java
StockMarket.java -- stock market has stocks and prices of stocks
Transaction.java -- holds date and amount
TransactionBuyStock.java -- holds stock
TransactionDeposit.java
TransactionSellStock.java -- holds stock
TransactionTransferIn.java
TransactionTransferOut.java
TransactionWithdrawal.java
JSONTools.java -- class with static methods to read from and write to JSON files. Each class has its own toJSON() and fromJSON() methods.

General classes:
Constants.java -- holds constants such as conversion rates between currencies, interest rates, etc.
General.java -- some general utility functions
IOTools.java -- some general utility functions related to I/O

Small helper classes:
Name.java - object for holding a user’s first and last names.
Password.java - object to hold a user’s password. Allows for specific conditional checks.
Permisions.java - a way to define what a user can do, say if you wanted different levels of customers, not really used fully, mostly for extendability.
Collateral.java -- collateral for a loan. Holds name and value of collateral
Credentials.java -- credentials for the user (name, username, password)
UName.java - object to hold a user’s username. Allows for specific conditional checks.
Stock.java -- holds name and ID. Does not have price because prices are set by the stock market

Collections (each one is just a collection of the respective objects):
Accounts.java
Customers.java
Stocks.java
Transactions.java

Behaviors (each one is a behavior of an account):
DepositBehavior.java
CanDepositBehavior.java
CannotDepositBehavior.java
TransferBehavior.java
CannotTransferBehavior.java
UncheckedTransferBehavior.java
WithdrawBehavior.java
CannotWithdrawBehavior.java
CanWithdrawBehavior.java
EndOfMonthBehavior.java
EndOfMonthInterestBehavior.java
EndOfMonthNegativeInterestBehavior.java
EndOfMonthNoBehavior.java

GUI:
General GUI:
BeginGUI.form - used to help create and modify the gui
BeginGUI.java - the “Launcher” for the app, creates a new Bank and manager if one doesnt exist, loads it if one does
GUI.form - used to help create and modify the gui
GUI.java - the GUI used to login or create a new customer

Manager GUI:
AddStockPanel.java -- panel for adding a new stock
EmitterPanel.java -- abstract class for a panel that emits text, and has listeners. Useful for the log of actions.
Listener.java -- listener (listens to EmitterPanel)
MainManagerFrame.java -- main frame
ManagerGUI.java -- entry point for manager GUI
ManagerToolbar.java -- toolbar panel
SetStockPricePanel.java -- panel to set stock prices
StockPanel.java -- panel holding AddStockPanel and SetStockPricePanel
TextPanel.java -- panel able to display text
TransactionPanel.java -- panel to get daily report
TimePanel.java -- panel to move time forward
CustomerPanel.java -- a panel to view the customer information

Customer GUI:
CustomerGUI.form - used to help create and modify the gui
CustomerGUI.java - go to Appendix A, lot to say here.
NewAccountGUI.form - used to help create and modify the gui
NewAccountGUI.java - presents an application used to created a new account
NewLoanGUI.form - used to help create and modify the gui
NewLoanGUI.java - simple GUI to request a new loan
TransferGUI.form - used to help create and modify the gui
TransferGUI.java - simple GUI to transfer money
