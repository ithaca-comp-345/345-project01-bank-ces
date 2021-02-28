
package edu.ithaca.dragon.bank;

public class BankTeller extends ATM{
    
    public savings createSavings(String accountID,double initialBalance)throws IllegalArgumentException{
            
            return new savings(accountID,initialBalance);
    }

    public checkings createChecking(String accountID,double initialBalance)throws IllegalArgumentException{
        return new checkings(accountID,initialBalance);
    }
    public void closeAccount(String type, BankAccount account)throws IllegalArgumentException{
        if(type.equals("savings")){
            account.nullSavings();
            
        }
        else if(type.equals("checkings")){
            account.nullChecking();
        }
        else{
            throw new IllegalArgumentException("Invalid Type");
        }
    }
}

