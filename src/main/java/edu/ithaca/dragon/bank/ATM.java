package edu.ithaca.dragon.bank;

public class ATM  {
    
    public static boolean isAmountValid(double balance){
        String s = "" + balance;
        String[] result = s.split("\\."); //Splits on the decimal and puts each side into result[1] (left half) and result[2] (right half)
        if(balance >=0 && result[1].length() <= 2){
          return true;
        }
       return false;
    }

    public double checkBalance (checkings account){
        
        if (account.getBalance() < 0){
            account.setBalance(0);
        }
        int temp = (int)(account.getBalance() * 100);
        account.setBalance(((double)temp) /100);
        return account.getBalance();
    }
    
    public double checkBalance (savings account){
        
        if (account.getBalance() < 0){
            account.setBalance(0);
        }
        int temp = (int)(account.getBalance() * 100);
        account.setBalance(((double)temp) /100);
        return account.getBalance();
    }

   
    public void withdraw (checkings account, double amount) throws InsufficientFundsException, IllegalArgumentException{
       if (!isAmountValid(amount)){
           throw new IllegalArgumentException("invalid amount");
       }
       if (amount > account.getBalance()){
        throw new InsufficientFundsException("not enough money");
    }
       else{
           double Final = account.getBalance()-amount;
           account.setBalance(Final);
       }
    }
    public void withdraw (savings account, double amount) throws InsufficientFundsException, IllegalArgumentException{
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("invalid amount");
        }
        if (amount > account.getBalance()){
         throw new InsufficientFundsException("not enough money");
     }
        else{
            double Final = account.getBalance()-amount;
            account.setBalance(Final);
        }
     }

    public void deposit (checkings account, double amount) throws IllegalArgumentException{
        if  (!isAmountValid(amount)){
                throw new IllegalArgumentException("invalid amount");
        }
        else{
            double Final = account.getBalance()+amount;
            account.setBalance(Final);
        }
    }
    public void deposit (savings account, double amount) throws IllegalArgumentException{
        if  (!isAmountValid(amount)){
                throw new IllegalArgumentException("invalid amount");
        }
        else{
            double Final = account.getBalance()+amount;
            account.setBalance(Final);
        }
    }   

    public void transfer (checkings accountFrom, checkings accountTo, double amount) throws InsufficientFundsException{
        //Implement
    }
   


}
