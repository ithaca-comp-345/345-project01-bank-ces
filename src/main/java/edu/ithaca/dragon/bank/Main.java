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

        //Handles the creation process, adding email, checking, and savings to the account
        BankAccount account = new BankAccount(email);
        checkings checking = new checkings(email, 1000);
        savings saving = new savings(email, 1000);
        account.addAcc(checking);
        account.addAcc(saving);

        System.out.println("Thank you for creating an account with us, as a token of good favor we have supplied both " +
                "your checking and savings accounts with $1000 each. Thank you for your time\n");

        return account;
    }

    public static boolean validChoice(String input){
        String[] choices = {"withdraw", "deposit", "transfer", "balance", "quit"};
        for (int i=0;i<choices.length;i++){
            if(input.equalsIgnoreCase(choices[i])){return true;}
        }
        return false;
    }

    public static boolean checkOrSave(String input){
        String[] accounts = {"checking", "saving"};
        for(int i=0;i<accounts.length;i++){
            if(input.equalsIgnoreCase(accounts[i])){return true;}
        }
        return false;
    }

    public static void balance(BankAccount account){
        Scanner scan = new Scanner(System.in);
        System.out.println("--Accounts--");
        System.out.println("Checking: $" + account.getChecking().getBalance()+
                "\nSavings: $" + account.getSaving().getBalance());
    }

    public static void withdraw(BankAccount account) throws InsufficientFundsException {
        ATM atm = new ATM();
        Scanner scan = new Scanner(System.in);

        System.out.println("From which account would you like to make a withdrawal?");
        System.out.println("--Accounts--");
        System.out.println("Checking\nSaving");
        String input = scan.nextLine();
        double amount = -4.00; //placeholder to enter loop

        while (!checkOrSave(input)) {
            System.out.println("Please enter a valid choice");
            input = scan.nextLine();
        }

        if(input.equalsIgnoreCase("checking")){
            while(!isAmountValid(amount)){ //check if amount is valid
                System.out.println("Please enter the amount: ");
                System.out.println("Current Balance: $"+account.getChecking().getBalance());
                amount = scan.nextDouble();

                if(account.getChecking().getBalance()-amount < 0){System.out.println("You cannot withdraw this much, please try again");}
                else if(!isAmountValid(amount)){System.out.println("This is an invalid amount, please try again");}
                else{
                    atm.withdraw(account.getChecking(), amount);
                    System.out.println("Withdrawal Complete");
                    System.out.println("New Balance: $"+account.getChecking().getBalance());
                }
            }
        }

        if(input.equalsIgnoreCase("saving")){
            while(!isAmountValid(amount)){ //check if amount is valid
                System.out.println("Please enter the amount: ");
                System.out.println("Current Balance: $"+account.getSaving().getBalance());
                amount = scan.nextDouble();
                if(account.getSaving().getBalance()-amount < 0){System.out.println("You cannot withdraw this much, please try again");}
                else if(!isAmountValid(amount)){System.out.println("This is an invalid amount, please try again");}
                else{
                    atm.withdraw(account.getSaving(), amount);
                    System.out.println("Withdrawal Complete");
                    System.out.println("New Balance: $"+account.getSaving().getBalance());
                }
            }
        }
    }

    public static void deposit(BankAccount account) throws InsufficientFundsException {
        ATM atm = new ATM();
        Scanner scan = new Scanner(System.in);

        System.out.println("From which account would you like to make a deposit?");
        System.out.println("--Accounts--");
        System.out.println("Checking\nSaving");
        String input = scan.nextLine();
        double amount = -4.00; //placeholder to enter loop

        while (!checkOrSave(input)) {
            System.out.println("Please enter a valid choice");
            input = scan.nextLine();
        }

        if(input.equalsIgnoreCase("checking")){
            while(!isAmountValid(amount)){ //check if amount is valid
                System.out.println("Please enter the amount: ");
                System.out.println("Current Balance: $"+account.getChecking().getBalance());
                amount = scan.nextDouble();
                if(!isAmountValid(amount)){System.out.println("This is an invalid amount, please try again");}
                else{
                    atm.deposit(account.getChecking(), amount);
                    System.out.println("Deposit Complete");
                    System.out.println("New Balance: $"+account.getChecking().getBalance());
                }
            }
        }

        if(input.equalsIgnoreCase("saving")){
            while(!isAmountValid(amount) || account.getSaving().getBalance()-amount < 0){ //check if amount is valid
                System.out.println("Please enter the amount: ");
                System.out.println("Current Balance: $"+account.getSaving().getBalance());
                amount = scan.nextDouble();
                if(!isAmountValid(amount)){System.out.println("This is an invalid amount, please try again");}
                else{
                    atm.deposit(account.getSaving(), amount);
                    System.out.println("Deposit Complete");
                    System.out.println("New Balance: $"+account.getSaving().getBalance());
                }
            }
        }
    }


    public static void menu(BankAccount account) throws InsufficientFundsException {
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to do?");
        String input = "bananas"; //placeholder to enter loop

        while(!input.equalsIgnoreCase("quit")){
            System.out.println("\n--Menu--\nBalance\nWithdraw\nDeposit\nTransfer\nQuit\n--------\n");
            input = scan.nextLine();
            if (!validChoice(input)){System.out.println("Please enter a valid choice");} //error catching

            if (input.equalsIgnoreCase("balance")){balance(account);} //balance sequence
            if (input.equalsIgnoreCase("withdraw")){withdraw(account);}
            if (input.equalsIgnoreCase("deposit")){deposit(account);}

        }
    }
    public static void main(String[] args) throws InsufficientFundsException {
        BankAccount account = create_account();
        menu(account);
    }

}