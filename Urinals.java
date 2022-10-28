import java.util.InputMismatchException;
import java.util.Scanner;

public class Urinals {
    final private static Scanner inputScanner = new Scanner(System.in);

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
        boolean isValid = true;
        return isValid;
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

                    }
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }
}
