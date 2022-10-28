import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Urinals {
    final private static Scanner inputScanner = new Scanner(System.in);
    public static String filename = "urinals.dat";

    public static void showMenu(){
        System.out.println("\nChoose from the below options : \n1. Input from User \n2. Input from the file \n3. Exit the program");
    }

    public static int getChoice(){
        int userChoice;
        try {
            userChoice = inputScanner.nextInt();
            return userChoice;
        }
        catch (InputMismatchException inputMismatchException) {
            System.out.println("Wrong Input Please enter again !!");
            return -1;
        }
    }

    public static String takeUrinalString(){
        return inputScanner.next();
    }

    public static boolean validateString(String inputString){
        for(char character : inputString.toCharArray()){
            if(character != '0' && character != '1'){
                return false;
            }
        }
        return true;
    }

    public static int countUrinals(String inputString){
        if(!validateString(inputString)){
            return -1;
        }
        int total = 0;
        char[] arrayOfUrinals = inputString.toCharArray();

        for(int index = 0; index < arrayOfUrinals.length; index++){
            if(arrayOfUrinals[index] == '0'){
                if(index == 0){
                    if(arrayOfUrinals.length == 1 || arrayOfUrinals[index + 1] == '0'){
                        arrayOfUrinals[index] = '1';
                        total++;
                    }
                }
                else if(arrayOfUrinals.length - 1 == index){
                    if(arrayOfUrinals[index - 1] == '0'){
                        arrayOfUrinals[index] = '1';
                        total++;
                    }
                }
                else{
                    if(arrayOfUrinals[index - 1] == '0' && arrayOfUrinals[index + 1] == '0'){
                        arrayOfUrinals[index] = '1';
                        total++;
                    }
                }
            }
        }
        return total;
    }

    public static ArrayList<String> getInputFromFile(String filename){
        ArrayList<String> listOfUrinalStrings = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line = bufferedReader.readLine();
        while (line != null || line.equals("-1")) {
            listOfUrinalStrings.add(line);
            line = bufferedReader.readLine();
        }
        return  listOfUrinalStrings;
    }
    public static void main(String[] args){
        while(true) {
            showMenu();
            int userChoice = getChoice();
            switch (userChoice){
                case -1:
                    break;
                case 1:
                    String inputString = takeUrinalString();
                    boolean isStringValid = validateString(inputString);
                    if(isStringValid){
                        int totalUrinals = countUrinals(inputString);
                        System.out.println("The total free urinals are : " + totalUrinals);
                    }
                case 2:
                    ArrayList<String> input = getInputFromFile(filename);
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }
}
