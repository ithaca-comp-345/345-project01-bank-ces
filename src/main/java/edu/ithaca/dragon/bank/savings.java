package edu.ithaca.dragon.bank;

public class savings extends ATM {
    private String accountID = "";
    private double balance; 

    public savings(String accountID, double startingBalance){
        if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Amount: " + startingBalance + " is invalid");
        }else{
            this.accountID = accountID;
            this.balance = startingBalance;
        }
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        this.balance = amount;
    }
    public void compounded(double time, double interest){
        if(!isAmountValidCompunded(time)){
            throw new IllegalArgumentException("Time: " + time + " is invalid");
        }else if(!isAmountValidCompunded(interest)){
            throw new IllegalArgumentException("Interest: " + interest + " is invalid");
        }
        //annual
        double A = balance * Math.pow(1 + ((interest/100) / 12), 12 * time);
        balance = Math.round(A * 100) / 100;
        
        
    }
    public static boolean isAmountValidCompunded(double num){
        String s = "" + num;
        
        
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(num > 0 && result[1].length() <= 2){
            return true;
        }
        return false;
    }

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        
        
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >= 0 && result[1].length() <= 2){
            return true;
        }
        return false;
    }
}
