package edu.ithaca.dragon.bank;

public class savings extends ATM {
    private String accountID = "";
    private double balance; 

    public savings(String accountID, double startingBalance){
        this.accountID = accountID;
        this.balance = startingBalance;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        this.balance = amount;
    }
}
