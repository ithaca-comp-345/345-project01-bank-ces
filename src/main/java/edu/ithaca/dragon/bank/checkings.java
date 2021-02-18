package edu.ithaca.dragon.bank;

public class checkings {
    private String accountID = "";
    private double balance; 

    public checkings(String accountID, double startingBalance){
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
