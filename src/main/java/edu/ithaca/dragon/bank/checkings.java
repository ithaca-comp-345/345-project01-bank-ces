package edu.ithaca.dragon.bank;

public class checkings{
    private String accountID = "";
    private double balance; 

    public checkings(String accountID, double startingBalance){
        if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Amount: " + startingBalance + " is invalid, cannot create account");
        }else{
            this.accountID = accountID;
            this.balance = startingBalance;
        }
        
    }
    public double getBalance(){
        return balance;
    }
    public String getAccountID(){
        return accountID;
    }

    public void setBalance(double amount){
        this.balance = amount;
    }

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        
        
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
            return true;
        }
        return false;
    }
}
