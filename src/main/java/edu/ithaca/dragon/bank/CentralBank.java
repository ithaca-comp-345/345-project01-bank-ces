package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class CentralBank {
    
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount findAccount (String accountID){
        for(int i = 0; i < bankAccounts.size(); i++){
            if(bankAccounts.get(i).getEmail().equals(accountID)){
                return bankAccounts.get(i);
            }
        }
        return null;
        
    }
    public void addAccount(BankAccount acc){
        bankAccounts.add(acc);
    }

    public ArrayList<BankAccount> getAllAccounts (){
        return  bankAccounts;
    }
}
