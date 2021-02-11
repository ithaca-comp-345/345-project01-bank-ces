package edu.ithaca.dragon.bank;

public class ATM {
    
    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

    public double checkBalance (BankAccount account){
       
        return account.getBalance();
    }
    
    public void withdraw (BankAccount account, double amount) throws InsufficientFundsException{
       //Implement
    }

    public void deposit (BankAccount account, double amount) throws InsufficientFundsException{
        //Implement
    }

    public void transfer (BankAccount account, double amount) throws InsufficientFundsException{
        //Implement
    }
   


}
