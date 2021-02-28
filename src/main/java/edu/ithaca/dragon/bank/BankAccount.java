package edu.ithaca.dragon.bank;
import java.util.ArrayList;

public class BankAccount {

    private String email;
    private checkings accCheck;
    private savings accSave;
    private ArrayList<String> transActHistory = new ArrayList<>();
    
    public BankAccount(String email){
        if (isEmailValid(email)){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }
    public void addAcc(checkings acc){
        this.accCheck = acc;
    }
    public void addAcc(savings acc){
        this.accSave = acc;
    }
    public void addTransAct(String trans){
        transActHistory.add(trans);
    }
    public ArrayList getTransActHistory(){
        return transActHistory;
    }
    
    public checkings getChecking(){
        return accCheck;
    }
    public savings getSaving(){
        return accSave;
    }
    
    public String getEmail(){
        return email;
    }

    public static boolean isEmailValid(String email){
        if ((email.indexOf('@') == -1) || (email.indexOf('@') == 0)){
            return false;
        }
        else if((email.indexOf('#')!= -1) ||(email.indexOf('-')!= -1) ){
            return false;
        }
        else if(email.indexOf('.')== 0 || (email.contains(".."))){
            return false;
        }
        else if(!(email.endsWith(".com"))){
            return false;
        }
        else if(email.charAt(email.indexOf('@')-1) == '#'||email.charAt(email.indexOf('@')-1) == '-' || email.charAt(email.indexOf('@')-1) == '.'  ){
            return false;
        }
        else {
            return true;
        }
    }

}
