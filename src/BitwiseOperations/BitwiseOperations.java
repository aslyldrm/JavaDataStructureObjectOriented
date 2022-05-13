package BitwiseOperations;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class does bitwise operations and prints
 * @author Asli Yildirim
 * @since 28.11.2021
 */
public class BitwiseOperations {
    private Boolean[] data = {false, false, false};
    private int[] dataTypes = new int[3];

    /**
     * This is a constructor and it does writeMenu method active
     */
    public BitwiseOperations() {
        writeMenu();
    }


    /**
     * This method prints the menu and sends the data to the action method
     */
    public void writeMenu() {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Give the type of input?" +
                    " int=0, string=1, double=2");
            int firstInput = sc.nextInt();
            System.out.println("Give the type of input?" +
                    " or=0, and=1, xor=2, complement=3, right shift=4, " +
                    "left shift=5");
            int secondInput = sc.nextInt();

            //I want to find length of my variable
            dataTypes[0] = 4;
            dataTypes[2] = 8;

            if (firstInput == 0) {
                data[firstInput] = true;
                if (secondInput == 0) {
                    //for or
                    System.out.println("First Integer Number :");
                    String firstInt = sc.next();
                    System.out.println("Second Integer Number :");
                    String secondInt = sc.next();
                    System.out.println(or(firstInt, secondInt));
                } else if (secondInput == 1) {
                    //for and
                    System.out.println("First Integer Number :");
                    String firstInt = sc.next();
                    System.out.println("Second Integer Number :");
                    String secondInt = sc.next();
                    System.out.println(and(firstInt, secondInt));
                } else if (secondInput == 2) {
                    //for xor
                    System.out.println("Integer Number :");
                    String firstInt = sc.next();
                    String secondInt = sc.next();
                    System.out.println(xor(firstInt, secondInt));

                } else if (secondInput == 3) {
                    //for complement
                    System.out.println("Integer Number :");
                    String firstInt = sc.next();
                    System.out.println(complement(firstInt));
                } else if (secondInput == 4) {
                    //for right shift
                    System.out.println("Integer Number : ");
                    String firstInt = sc.next();
                    int shift = sc.nextInt();
                    System.out.println(rs(firstInt, shift));

                } else if (secondInput == 5) {
                    //for left shift
                    System.out.println("Integer Number :");
                    String firstInt = sc.next();
                    System.out.println("Shift Number");
                    int shift = sc.nextInt();
                    System.out.println(ls(firstInt, shift));

                } else {
                    System.err.println("You chose wrong choice.Please try again");
                }
            } else if (firstInput == 1) {
                data[firstInput] = true;
                if (secondInput == 0) {
                    //for or

                    System.out.println("First String :");
                    String firstInt = sc.next();
                    System.out.println("Second String :");
                    String secondInt = sc.next();
                    dataTypes[1] = Math.max(firstInt.length(), secondInt.length());
                    System.out.println(or(firstInt, secondInt));
                } else if (secondInput == 1) {
                    //for and
                    System.out.println("First String:");
                    String firstInt = sc.next();
                    System.out.println("Second String:");
                    String secondInt = sc.next();
                    dataTypes[1] = Math.max(firstInt.length(), secondInt.length());

                    System.out.println(and(firstInt, secondInt));
                } else if (secondInput == 2) {
                    //for xor
                    System.out.println("First String :");
                    String firstInt = sc.next();
                    System.out.println("Second String:");
                    String secondInt = sc.next();
                    dataTypes[1] = Math.max(firstInt.length(), secondInt.length());
                    System.out.println(xor(firstInt, secondInt));

                } else {
                    System.err.println("You chose wrong choice.Please try again");
                }

            } else if (firstInput == 2) {
                data[firstInput] = true;

                if (secondInput == 0) {
                    //for or
                    System.out.println("First Double Number:");
                    String firstInt = sc.next();
                    System.out.println("Second Double Number:");
                    String secondInt = sc.next();
                    System.out.println(or(firstInt, secondInt));
                } else if (secondInput == 1) {
                    //for and
                    System.out.println("First Double Number:");
                    String firstInt = sc.next();
                    System.out.println("Second Double Number:");
                    String secondInt = sc.next();
                    System.out.println(and(firstInt, secondInt));
                } else if (secondInput == 2) {
                    //for xor
                    System.out.println("Double Number:");
                    String firstInt = sc.next();
                    System.out.println("Double Number:");
                    String secondInt = sc.next();
                    System.out.println(xor(firstInt, secondInt));

                } else {
                    System.err.println("You chose wrong choice.Please try again");
                }

            } else {
                System.err.println("You chose wrong choice.Please try again");
            }
        }

        catch (InputMismatchException | NumberFormatException f){
            System.err.println("It is not true format");
        }

    }


    /**
     * This method do left shift and return the result as a string and 8 byte representation
     * @param input It is number to convert to binary number
     * @param number It is the number indicating how many times to make a left shift.
     * @return result of operation with input,result with representation binary numbers
     */
    public String ls(String input, int number) {
        String txt = "";

        int result = Integer.parseInt(input) << number;
        String binaryInteger = Integer.toBinaryString(result);
        txt = completeZeros(binaryInteger,dataTypes[0]);
        txt = splitStringEightPieces(txt);

        return input +  " << " + number +" = " + txt + " = " + result ;
    }


    /**
     * This method do right shift and return the result as a string and 8 byte representation
     * @param input It is number to convert to binary number
     * @param number It is the number indicating how many times to make a right shift.
     * @return result of operation  with input,result with representation binary numbers
     */
    public String rs(String input, int number) {
        String txt = "";
        int result = Integer.parseInt(input) >> number;
        String binaryInteger = Integer.toBinaryString(result);
        txt = completeZeros(binaryInteger,dataTypes[0]);
        txt = splitStringEightPieces(txt);

        return input +  " >> " + number +" = " + txt + " = " + result ;
    }

    /**
     * This method do complement and return the result as a string and 8 byte representation
     * @param firstInput It is number to convert to binary number
     * @return result of operation with input,result with representation binary numbers
     */

    public String complement(String firstInput) {
        String txt = "";
        String a = "";
        String forFirstInput = Integer.toString(Integer.parseInt(firstInput), 2);
        int result = ~Integer.parseInt(firstInput);
        String resultType = Integer.toBinaryString(result);

        resultType = completeZeros(resultType,dataTypes[0]);
        resultType = splitStringEightPieces(resultType);

        forFirstInput = completeZeros(forFirstInput,dataTypes[0]);
        forFirstInput = splitStringEightPieces(forFirstInput);

        return "~" + txt+forFirstInput + " = " + resultType +" = " +  result;

    }



    /**
     * This method completes binary representation to string 64 or 32 bit
     * @param binaryIntDoubleString It is binary number to convert of 8 byte representation
     * @param dataType Specifies what type the data is
     * @return 8 byte representation of input
     */
    public String completeZeros(String binaryIntDoubleString,int dataType){
        String txt = "";

        int a = binaryIntDoubleString.length();
        if (data[2]){
            for (int i = 0; i < 8 * dataType - binaryIntDoubleString.length(); i++) {
                txt = "0" + txt;
            }
            txt = txt + binaryIntDoubleString;
        }
        else {
            for (int i = 0; i < 8 * dataType - binaryIntDoubleString.length(); i++) {
                txt = txt + "0";
            }
            txt = txt + binaryIntDoubleString;
        }

        return txt;
    }

    /**
     * This method splits string to 8 bytes and leaves a space between two byte
     * @param binaryString String to be split into eight parts
     * @return A binary number divided into eight parts and joined with spaces.
     */
    public String splitStringEightPieces(String binaryString){

        String txt = "";
        for(int i = 1; i <= binaryString.length(); i++) {
            if (i % 8 == 0) {
                if (i == binaryString.length()) {
                    txt = txt + binaryString.substring(i - 8, i);
                } else {
                    txt = txt + binaryString.substring(i - 8, i) + " ";
                }
            }
        }
        return txt;
    }



    /**
     * Splits the word into letters. Converts letters to ASCII and converts all letters to 8 bits
     * @param firstInput word to be split into characters
     * @return Binary number of word
     */
    public String completeZerosForString(String firstInput) {
        String[] firstInp = new String[dataTypes[1]];
        String inp1 = "";
        for(int i = dataTypes[1] - (firstInput.length()); i < firstInp.length;i++){

            firstInp[i] = Integer.toBinaryString(firstInput.charAt(i-(dataTypes[1] - (firstInput.length()))));

            firstInp[i] = String.format("%8s",firstInp[i]).replaceAll(" ","0");

        }
        if(dataTypes[1] - (firstInput.length()) != 0){
            for(int i = 0; i < dataTypes[1] - (firstInput.length()); i++){
                firstInp[i] = "00000000";
            }
        }

        for(int i = 0, j = 0; i< dataTypes[1];i++,j++){
            inp1 = inp1 + firstInp[i];


        }
        return inp1;
    }

    /**
     *Method takes two data and converts it to binary then it sends another
     * methods for (xor) calculation and gets string
     * @param firstInput First data to be converted to binary
     * @param secondInput Second data to be converted to binary
     * @return Shows binary representation of data and operation result
     */
    public String xor(String firstInput, String secondInput) {
        String txt ="";
        if(data[0]){
            String forFirstInput = Integer.toString(Integer.parseInt(firstInput),2);
            String forSecondInput = Integer.toString(Integer.parseInt(secondInput),2);
            txt = calculateBitwiseOperations(forFirstInput,forSecondInput, dataTypes[0], "^");

        }
        else if (data[1]) {

            String firstInp = completeZerosForString(firstInput);
            String secondInp = completeZerosForString(secondInput);

            txt = calculateBitwiseOperations(firstInp,secondInp,dataTypes[1],"^");
        }
        else {

            firstInput =
                    Long.toBinaryString(Double.doubleToLongBits
                            (Double.parseDouble(firstInput)));
            secondInput = Long.toBinaryString(Double.doubleToLongBits
                    (Double.parseDouble(secondInput)));
            txt = CalculateDoubleValues(firstInput,secondInput,"^");


        }
        return txt;
    }

    /**
     * Method takes two data and converts it to binary then it sends another
     *  methods for (and) calculation and gets string
     * @param firstInput First data to be converted to binary
     * @param secondInput Second data to be converted to binary
     * @return Shows binary representation of data and operation result
     */
    public String and(String firstInput, String secondInput) {
        String txt ="";
        if(data[0]){
            String forFirstInput = Integer.toString(Integer.parseInt(firstInput),2);
            String forSecondInput = Integer.toString(Integer.parseInt(secondInput),2);
            txt = calculateBitwiseOperations(forFirstInput,forSecondInput,dataTypes[0], "&");

        }
        else if (data[1]){
            String firstInp = completeZerosForString(firstInput);
            String secondInp = completeZerosForString(secondInput);

            txt = calculateBitwiseOperations(firstInp,secondInp,dataTypes[1],"&");



        }
        else {

            firstInput =
                    Long.toBinaryString(Double.doubleToLongBits
                            (Double.parseDouble(firstInput)));
            secondInput = Long.toBinaryString(Double.doubleToLongBits
                    (Double.parseDouble(secondInput)));
            txt = CalculateDoubleValues(firstInput,secondInput,"&");

        }
        return txt;
    }

    /**
     * Method takes two data and converts it to binary then it sends another
     * methods for (or) calculation and gets string
     * @param firstInput First data to be converted to binary
     * @param secondInput Second data to be converted to binary
     * @return Shows binary representation of data and operation result
     */
    public String or(String firstInput, String secondInput) {
        String txt = "";
        if(data[0]){
            String forFirstInput = Integer.toString(Integer.parseInt(firstInput),2);
            String forSecondInput = Integer.toString(Integer.parseInt(secondInput),2);
            txt = calculateBitwiseOperations(forFirstInput,forSecondInput, dataTypes[0],"|");

        }
        else if (data[1]){
            String firstInp = completeZerosForString(firstInput);
            String secondInp = completeZerosForString(secondInput);


            txt = calculateBitwiseOperations(firstInp,secondInp,dataTypes[1],"|");

        }
        else {
            firstInput =
                    Long.toBinaryString(Double.doubleToLongBits
                            (Double.parseDouble(firstInput)));

            secondInput = Long.toBinaryString(Double.doubleToLongBits
                    (Double.parseDouble(secondInput)));
            txt = CalculateDoubleValues(firstInput,secondInput,"|");
        }

        return txt;

    }


    /**
     * Method does bitwise operations for binary numbers and prints the result
     * @param forFirstInput First binary number
     * @param forSecondInput Second binary number
     * @param operator Equation operator for bitwise operation
     * @return Shows binary representation of data and operation result
     */
    public String CalculateDoubleValues(String forFirstInput,
                                        String forSecondInput, String operator){
        String txt = "";
        String txt1 = "";
        String txt2 = "";
        String result = "";

        if(operator.equals("|")){
            result = Long.toBinaryString(Long.parseLong(forFirstInput,2)|
                    Long.parseLong(forSecondInput,2));

        }
        else if(operator.equals("&")) {

            result = Long.toBinaryString(Long.parseLong(forFirstInput,2)&
                    Long.parseLong(forSecondInput,2));

        }

        else {
            result = Long.toBinaryString(Long.parseLong(forFirstInput,2)^
                    Long.parseLong(forSecondInput,2));

        }

        txt = splitStringEightPieces(completeZeros(forFirstInput,dataTypes[2]));
        txt1 = splitStringEightPieces(completeZeros(forSecondInput,
                dataTypes[2]));
        txt2 = splitStringEightPieces(completeZeros(result,dataTypes[2]));
        return txt + " " + operator + "\n" + txt1 + "\n" +
                "--------------------------------------\n" +
                txt2;


    }


    /**
     * The method performs bitwise operations with the entered data and turns the result into a sentence.
     * @param forFirstInput The first binary number to do operation
     * @param forSecondInput The second binary number to do operation
     * @param dataType Specifies how many bytes of data
     * @param s Equation operator for bitwise operation
     * @return Shows binary representation of data and operation result
     */
    public String calculateBitwiseOperations(String forFirstInput,
                                             String forSecondInput, int dataType, String s) {
        String txt = "";
        String txt1 = "";
        String txt2 = "";
        String resultType= "";
        String result ="";

        if(dataType == dataTypes[0]){
            if(s.equals("|")) {
                resultType = Integer.toString(Integer.parseInt(forFirstInput,2) |
                        Integer.parseInt(forSecondInput,2));
                result = Integer.toString(Integer.parseInt(resultType),2);


            }
            else if (s.equals("&")){

                resultType = Integer.toString(Integer.parseInt(forFirstInput,2) &
                        Integer.parseInt(forSecondInput,2));
                result = Integer.toString(Integer.parseInt(resultType),2);
            }
            else if(s.equals("^")){
                resultType = Integer.toString(Integer.parseInt(forFirstInput,2) ^
                        Integer.parseInt(forSecondInput,2));
                result = Integer.toString(Integer.parseInt(resultType),2);

            }

        }
        else if(dataType== dataTypes[1]){
            BigInteger firstString = new BigInteger(forFirstInput, 2);
            BigInteger secondString = new BigInteger(forSecondInput, 2);

            if(s.equals("|")){
                BigInteger biggerInteger = firstString.or(secondString);
                result = biggerInteger.toString(2);
                resultType = convertStringToASCII
                        (splitStringEightPieces(completeZeros(result,dataTypes[1])));


            }
            else if(s.equals("&")) {
                BigInteger biggerInteger = firstString.and(secondString);
                result = biggerInteger.toString(2);
                resultType = convertStringToASCII
                        (splitStringEightPieces(completeZeros(result,dataTypes[1])));



            }

            else {
                BigInteger biggerInteger = firstString.xor(secondString);
                result = biggerInteger.toString(2);
                resultType = convertStringToASCII
                        (splitStringEightPieces(completeZeros(result,dataTypes[1])));




            }

        }


        txt = splitStringEightPieces(completeZeros(forFirstInput,dataType));
        txt1 = splitStringEightPieces(completeZeros(forSecondInput,dataType));
        txt2 = splitStringEightPieces(completeZeros(result,dataType));


        if(data[0]) {
            return txt + " " + s + "\n" + txt1 + "\n" + "--------------------------------------\n" +
                    txt2 + " = " + resultType;
        }
        else {
            return txt + " " + s + "\n" + txt1 + "\n" + "------------------------------------------"
                    + "-----------------------------\n" + txt2 + " = " + resultType;
        }
    }


    /**
     * Method converts entered binary number to ASCII characters
     * @param txt Binary number to convert to characters
     * @return Returns the result of the operation in word form
     */
    public String convertStringToASCII(String txt) {
        String text ="";
        String[] split = txt.split(" ");

        for( int i = 0; i < split.length; i++){

            text = text + (char) Integer.parseInt(split[i],2);
        }
        return text;

    }
}
