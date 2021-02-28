
package edu.ithaca.dragon.bank;

public class BankTeller extends ATM{
    
    public static void createAccount(String accountID,String type,double initialBalance)throws IllegalArgumentException{
        if(type == "savings"){
            savings AccOne = new savings(accountID,initialBalance);
        }
        if(type == "checking"){
            checkings AccOne = new checkings(accountID, initialBalance);
        }
        else{
            throw new IllegalArgumentException("Account type is invalid");
        }
    }

    public static void closeAccount(String accountID){
        //AccName = null;
    }
}

