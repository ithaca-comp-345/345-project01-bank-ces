package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class ATM {
    
    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

    public double checkBalance (BankAccount bankAccount,checkings account){
        
        if (account.getBalance() < 0){
            account.setBalance(0);
        }
        int temp = (int)(account.getBalance() * 100);
        account.setBalance(((double)temp) /100);
        bankAccount.addTransAct("Checking's checked bal of: " + account.getBalance());
        return account.getBalance();
    }
    
    public double checkBalance (BankAccount bankAccount,savings account){
        
        if (account.getBalance() < 0){
            account.setBalance(0);
        }
        int temp = (int)(account.getBalance() * 100);
        account.setBalance(((double)temp) /100);
        bankAccount.addTransAct("Savings's checked bal of: " + account.getBalance());
        return account.getBalance();
    }

   
    public void withdraw (BankAccount bankAccount,checkings account, double amount) throws InsufficientFundsException, IllegalArgumentException{
       if (!isAmountValid(amount)){
           throw new IllegalArgumentException("invalid amount");
       }
       if (amount > account.getBalance()){
        throw new InsufficientFundsException("not enough money");
    }
       else{
           double Final = account.getBalance()-amount;
           bankAccount.addTransAct("Checkings's withdrew amount of: " + amount);
           account.setBalance(Final);
       }
    }
    public void withdraw (BankAccount bankAccount, savings account, double amount) throws InsufficientFundsException, IllegalArgumentException{
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("invalid amount");
        }
        if (amount > account.getBalance()){
         throw new InsufficientFundsException("not enough money");
     }
        else{
            double Final = account.getBalance()-amount;
            bankAccount.addTransAct("Savings's withdrew amount of: " + amount);
            account.setBalance(Final);
        }
     }

    public void deposit (BankAccount bankAccount, checkings account, double amount) throws IllegalArgumentException{
        if  (!isAmountValid(amount)){
                throw new IllegalArgumentException("invalid amount");
        }
        else{
            double Final = account.getBalance()+amount;
            bankAccount.addTransAct("Checkings's deposited amount of: " + amount);
            account.setBalance(Final);
        }
    }
    public void deposit (BankAccount bankAccount, savings account, double amount) throws IllegalArgumentException{
        if  (!isAmountValid(amount)){
                throw new IllegalArgumentException("invalid amount");
        }
        else{
            double Final = account.getBalance()+amount;
            bankAccount.addTransAct("Savings's deposited amount of: " + amount);
            account.setBalance(Final);
        }
    }   

    //six withdrawals or transfers per month own account
    public void transfer (BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount, savings accountFrom, checkings accountTo) throws InsufficientFundsException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot deposit");
        }else if(amount > accountFrom.getBalance()){
            throw new InsufficientFundsException("Not enough money to transfer");
        }
        bankAccountFrom.addTransAct("Transfered " + amount + "to account id: " + accountTo.getAccountID());
        bankAccountTo.addTransAct("Received " + amount + "from account id: " + accountFrom.getAccountID());
        accountTo.setBalance(accountTo.getBalance() +  amount);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
    }
    public void transfer (BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount, checkings accountFrom, savings accountTo) throws InsufficientFundsException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot deposit");
        }else if(amount > accountFrom.getBalance()){
            throw new InsufficientFundsException("Not enough money to transfer");
        }
        bankAccountFrom.addTransAct("Transfered " + amount + "to account id: " + accountTo.getAccountID());
        bankAccountTo.addTransAct("Received " + amount + "from account id: " + accountFrom.getAccountID());
        accountTo.setBalance(accountTo.getBalance() +  amount);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
    }

    public void transfer (BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount, checkings accountFrom, checkings accountTo) throws InsufficientFundsException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot deposit");
        }else if(amount > accountFrom.getBalance()){
            throw new InsufficientFundsException("Not enough money to transfer");
        }
        bankAccountFrom.addTransAct("Transfered " + amount + "to account id: " + accountTo.getAccountID());
        bankAccountTo.addTransAct("Received " + amount + "from account id: " + accountFrom.getAccountID());
        accountTo.setBalance(accountTo.getBalance() +  amount);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
    }
    
    public void transfer (BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount, savings accountFrom, savings accountTo) throws InsufficientFundsException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot deposit");
        }else if(amount > accountFrom.getBalance()){
            throw new InsufficientFundsException("Not enough money to transfer");
        }
        bankAccountFrom.addTransAct("Transfered " + amount + "to account id: " + accountTo.getAccountID());
        bankAccountTo.addTransAct("Received " + amount + "from account id: " + accountFrom.getAccountID());
        accountTo.setBalance(accountTo.getBalance() +  amount);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
    }
    public void checkTransActionHistory(BankAccount account){
        ArrayList<String> transActHistory = account.getTransActHistory();
        for(int i = 0; i < transActHistory.size(); i++){
            System.out.println(transActHistory.get(i));
        }
        
    }
   


}
