package edu.ithaca.dragon.bank;
import java.util.Scanner;

public class Main {
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
    public static boolean isAmountValid(double amount) {
        if (amount < 0) {
            return false;
        } //Checking for negative numbers
        else if ((amount * 1000 % 10) != 0) { return false; } //Checking to make sure that there aren't more than 2 decimal places
        return true;
    }

    public static BankAccount create_account(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String email = "asdf";

        while(!isEmailValid(email)){
            System.out.println("Enter Email: ");  // Ask for email
            email = myObj.nextLine();  // Read user input on the nextLine
            if(!isEmailValid(email)){ System.out.println("Email invalid, try again"); }
        }
        BankAccount account = new BankAccount(email);

        return account;
    }

    public static boolean validChoice(String input){
        String[] choices = {"withdraw", "deposit", "transfer", "quit"};
        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){return true;}
        }
        return false;
    }

    public static void withdraw(BankAccount account){
        System.out.println("Welcome to the withdraw menu, from which account would you like to withdraw?");
        System.out.println("\nCheckings: "+account.getChecking());
        System.out.println("\nSavings: "+account.getSaving());
    }

    public static void menu(BankAccount account){
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to do?");
        System.out.println("\n--Menu--\nWithdraw\nDeposit\nTransfer\nQuit\n--------\n");
        String input = scan.nextLine();

        while(!validChoice(input)){ //confirmation loop, checks the choice against the options
            System.out.println("Please choose a valid option");
            System.out.println("\n--Menu--\nWithdraw\nDeposit\nTransfer\nQuit\n--------\n");
            input = scan.nextLine();
        }
        if (input.equalsIgnoreCase("withdraw")){withdraw(account);} //Withdraw sequence
    }

    public static void main(String[] args) {
        BankAccount account = create_account();
        menu(account);
    }

}