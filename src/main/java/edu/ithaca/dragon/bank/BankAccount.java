package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;
    private checkings accCheck;
    private savings accSave;

    
    public BankAccount(String email, double startingBalance){
        this.accCheck = new checkings("1234", startingBalance);
        this.accSave = new savings("1234", startingBalance);

        if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Amount: " + startingBalance + " is invalid, cannot create account");
        }
        else if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }
    

    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        
        
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
            return true;
        }
        return false;
    }

    public checkings getChecking(){
        return accCheck;
    }
    public savings getSaving(){
        return accSave;
    }
    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        this.balance = amount;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance && isAmountValid(amount)){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
    public void deposit(double amount) {
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("amount is invalid");
        }
        else{
            balance+=amount;
        }
    }

    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            return true;
        }
    }

}
