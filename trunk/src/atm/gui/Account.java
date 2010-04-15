package atm.gui;

import atm.gui.db.AccountDAO;

public class Account 
{
   private int accountNumber;
   private String fullName;
   private int pin;
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available + pending deposits

   /** Account constructor initializes attributes **/
   public Account( int theAccountNumber, String theFullName, int thePIN,
		   			double theAvailableBalance, double theTotalBalance ) {
	   accountNumber = theAccountNumber;
	   fullName = theFullName;
	   pin = thePIN;
	   availableBalance = theAvailableBalance;
	   totalBalance = theTotalBalance;
   }

   /** determines whether a user-specified PIN matches PIN in Account **/
   public boolean validatePIN(int userPIN) {
	   if ( userPIN == pin )
		   return true;
	   else
		   return false;
   }
   
   /** change PIN code **/
   public void changePIN(int newPIN) {
	   pin = newPIN;
	   
	   //test - update db
	   AccountDAO accountDAO = new AccountDAO();
	   accountDAO.changePIN(accountNumber, newPIN);
   }
   /** returns available balance **/
   public double getAvailableBalance() {
	   return availableBalance;
   }

   /** returns the total balance **/
   public double getTotalBalance() {
	   return totalBalance;
   }

   /** credits an amount to the account **/
   public void credit(double amount) {
	   totalBalance += amount;
   }

   public void debit(double amount) {
	   availableBalance -= amount;
	   totalBalance -= amount;
      
	   //test - update db
	   AccountDAO accountDAO = new AccountDAO();
	   accountDAO.updateAccount(this);
   }

   /** returns account number **/
   public int getAccountNumber() {
      	return accountNumber;  
   }   
   
   /** return user full name **/
   public String getFullName() {
	   	return fullName;
   }
   
}