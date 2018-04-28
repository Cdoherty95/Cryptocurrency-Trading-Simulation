package application;

import model.DaoUsers;

public interface UserOperations {
	public void viewTransactionHistory();
	public void viewCurrencyHistory();
	public void buy();
	public void sell();
	public void deposit();
	public void withdraw();
	public void addBankAccount();
	public void alterPaymentDetails();
	public void updateAccountInformation();
	DaoUsers daoUsers = new DaoUsers();
}
