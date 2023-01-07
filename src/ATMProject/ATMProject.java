package ATMProject;

import java.io.*;
import java.util.Scanner;

public class ATMProject {
    public static void main(String[] args) throws IOException {

        String baseFileName = args[0];
        String accountInfoOut = baseFileName+  "_AccountInfoOut.txt";
        String transferOut = baseFileName+"_log.txt";

        String transferInfo = "Assignment4_TransferInfo.txt";
        String accountInfo = "Assignment4_AccountInfo.txt";

        File transfer = new File(transferInfo);

        int numOfAccounts = ATMProject.countAccounts(accountInfo);


        int[] acctNums = new int[numOfAccounts];
        String[] names = new String[numOfAccounts];
        String[] surnames = new String[numOfAccounts];
        double[] balances = new double[numOfAccounts];

        int countNumberForTransfer = countAccounts(transferInfo);
        String[]  tr = new String[countNumberForTransfer];
        int[] numTo = new int[countNumberForTransfer];
        int[] numFrom = new int[countNumberForTransfer];
        double[] money = new double[countNumberForTransfer];

        readAccountInfo(acctNums,names,surnames,balances,accountInfo);

        try (Scanner sc = new Scanner(transfer)) {
            int i = 0;

            while (sc.hasNext()) {
                tr[i] = sc.next();

                numFrom[i] = sc.nextInt();
                numTo[i] = sc.nextInt();
                money[i] = sc.nextDouble();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String[] result = new String[tr.length];
        int[] transferResult = new int[tr.length];
        for (int i = 0; i < numTo.length; i++) {
            transferResult[i]=  transfer(acctNums,balances,numFrom[i],numTo[i],money[i]);

            if(transferResult[i]== 0){
                result[i] = "STX - Transfer Successful";
            }
            else if ( transferResult[i]==1){
                result[i] = "TNF - To Account not found";
            }
            else if (transferResult[i]== 2) {
                result[i] = "FNF - From Account not found";
            }
            else {
                result[i] = "NSF - Insufficient Funds";
            }
        }

        File log = new File(transferOut);
        PrintWriter output = new PrintWriter(log);


        for (int i = 0; i < numTo.length; i++) {
            output.println("Transfer " + tr[i] + " " + "resulted in code " + transferResult[i]+ ": " + result[i]);
        }
        output.close();
       writeAccountInfo(acctNums,names,surnames,balances,accountInfoOut);
    }

    public static void writeAccountInfo(int[] acctNums, String[] names, String[] surnames,
                                        double[] balances, String filename) throws FileNotFoundException {


        File account = new File(filename);
        PrintWriter output = new PrintWriter(account);

        for (int i = 0; i < acctNums.length; i++){

            output.println(acctNums[i] + " "+ names[i] + " " + surnames[i] + " " +
                    balances[i]);
        }
        output.close();
    }

    public static int transfer(int[] acctNums, double[] balances, int acctNumFrom,
                               int acctNumTo, double amount) {
        int valueNumFrom = 0;
        for (int i = 0; i < balances.length; i++) {
            if(acctNums[i] == acctNumFrom){
                valueNumFrom=i;
                break;
            }
        }
        int valueNumTo = 0;
        for (int i = 0; i < balances.length; i++) {
            if (acctNums[i] == acctNumTo) {
                valueNumTo = i;
                break;
            }
        }
        if(findAcct(acctNums,acctNumTo)== -1){
            return 1;
        }
        else if(findAcct(acctNums,acctNumFrom)== -1){
            return 2;
        }
        else if(!isWithdrawalValid(balances[valueNumFrom],amount)){
            return 3;

        }
        else {
            if(withdrawal(balances,valueNumFrom,amount) && deposit(balances,valueNumTo,amount) ){
                return 0;
            }
            else {
                System.out.println("It is not true value");
            }

        }

        return 0;
    }

    public static boolean withdrawal(double[] balances, int index, double amount) {
        if(isWithdrawalValid(balances[index],amount)){
            balances[index]= balances[index] - amount;
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean deposit(double[] balances, int index, double amount) {
        if(isDepositValid(amount)){
            balances[index]= amount + balances[index];
            return true;

        }
        else {
            return false;
        }

    }

    public static void readAccountInfo(int[] acctNums, String[] names,
                                       String[] surnames, double[] balances,
                                       String filename) {

        File file = new File(filename);
        try (Scanner sc = new Scanner(file)) {
            int i = 0;

            while (sc.hasNext() && (i != acctNums.length)) {
                acctNums[i] = sc.nextInt();
                names[i] = sc.next();
                surnames[i] = sc.next();
                balances[i] = sc.nextDouble();
                i++;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int countAccounts(String filename) throws IOException {
        File file = new File(filename);

        int numberOfLines = 0;
        BufferedReader bufferedReader = new BufferedReader
                (new FileReader(file));
        while(bufferedReader.readLine() != null) {
            numberOfLines++;
        }
        bufferedReader.close();
        return numberOfLines;
    }




    public static int findAcct(int[] acctNums, int acctNum) {

        for (int num : acctNums) {
            if (num == acctNum) {
                return 1;
            }
        }
        return -1;
    }

    public static boolean  isDepositValid(double amount){
        return amount > 0;
    }
    public static boolean isWithdrawalValid(double balance,double amount){
        return amount > 0 && balance > amount;
    }

}

