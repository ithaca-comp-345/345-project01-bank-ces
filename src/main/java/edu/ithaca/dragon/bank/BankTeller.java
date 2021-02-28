
package edu.ithaca.dragon.bank;

public class BankTeller extends ATM{
    
    public savings createSavings(String accountID,double initialBalance)throws IllegalArgumentException{
            savings AccOne = new savings(accountID,initialBalance);
            return AccOne;
    }

    public checkings createChecking(String accountID,double initialBalance)throws IllegalArgumentException{
        checkings AccOne = new checkings(accountID,initialBalance);
        return AccOne;
    }
    public void closeAccount(String type, BankAccount account)throws IllegalArgumentException{
        if(type == "savings"){
            account.nullChecking();
        }
        else if(type == "checkings"){
            account.nullSavings();
        }
        else{
            throw new IllegalArgumentException("Invalid Type");
        }
    }
}

