import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Urinals {
    final private static Scanner inputScanner = new Scanner(System.in);
    public static String filename = "urinals.dat";
    public static String defaultResultFileName = "rule";

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
        if(inputString.length() ==0 || inputString.length() >20){
            return false;
        }
        int index = 0;
        for(char character : inputString.toCharArray()){
            if(character != '0' && character != '1'){
                return false;
            }
            else{
                if(inputString.charAt(index) == '1'){
                    if(index == 0 && index != inputString.length() - 1){
                        if(inputString.charAt(index + 1) == '1'){
                            return false;
                        }
                    }
                    else if(index == inputString.length() - 1){
                        if(index!= 0 && inputString.charAt(index - 1) == '1'){
                            return false;
                        }
                    }
                    else{
                        if(inputString.charAt(index + 1) == '1' || inputString.charAt(index - 1) == '1'){
                            return false;
                        }
                    }
                }
            }
            index++;
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

    public static List<String> getInputFromFile(String filename){
        List<String> listOfUrinalStrings = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            while (line != null) {
                if(line.equals("-1")){
                    break;
                }
                listOfUrinalStrings.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("File does not exist");
        } catch (IOException ioException) {
            System.out.println("I/O Exception occurred");
            throw new RuntimeException(ioException);
        }
        return  listOfUrinalStrings;
    }

    public static String getRecentFileName(){
        int counter = 1;
        File file = new File(defaultResultFileName + ".txt");
        String newFileName = defaultResultFileName;
        while(file.exists()) {
            newFileName = defaultResultFileName;
            newFileName = "%s%d".formatted(newFileName, counter++);
            file = new File(newFileName + ".txt");
        }
        return newFileName + ".txt";
    }

    public static void writeToFile(List<Integer> listOfLines){
        try {
            FileWriter writer = new FileWriter(defaultResultFileName);
            for(Integer line: listOfLines) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        showMenu();
        int userChoice = getChoice();
        boolean isStringValid;
        switch (userChoice) {
            case 1 -> {
                String inputString = takeUrinalString();
                isStringValid = validateString(inputString);
                if (isStringValid) {
                    int totalUrinals = countUrinals(inputString);
                    System.out.println("The total free urinals are : " + totalUrinals);
                } else {
                    System.out.println("The string is not valid!!");
                }
            }
            case 2 -> {
                List<String> input = getInputFromFile(filename);
                List<Integer> listOfFreeUrinals = new ArrayList<>();
                for (String string : input) {
                    isStringValid = validateString(string);
                    if (isStringValid) {
                        int totalFreeUrinals = countUrinals(string);
                        listOfFreeUrinals.add(totalFreeUrinals);
                    } else {
                        listOfFreeUrinals.add(-1);
                    }
                }
                defaultResultFileName = getRecentFileName();
                writeToFile(listOfFreeUrinals);
            }
            case 3 -> System.exit(0);
            default -> {
            }
        }
    }
}
