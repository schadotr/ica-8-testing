import java.util.Scanner;

public class Urinals {
    final private static Scanner inputScanner = new Scanner(System.in);

    public static void showMenu(){
        System.out.println("\nChoose from the below options : \n1. Input from User \n2. Input from the file \n3. Exit the program");
    }

    public static int getChoice(){
        int userChoice = inputScanner.nextInt();
        return userChoice;
    }

    public static void main(String[] args){
        while(true) {
            showMenu();
            getChoice();
        }
    }
}
