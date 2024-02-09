package se.ju23.typespeeder.io;
import java.util.Scanner;

public class ConsoleIO implements IO{
    private Scanner scanner;

    public ConsoleIO(){
        this.scanner = new Scanner(System.in);
    }

    public void introText(){
        System.out.println("Welcome to");
        System.out.println( " ______              ____                __       \n" +
                "/_  __/_ _____  ___ / __/__  ___ ___ ___/ /__ ____\n" +
                " / / / // / _ \\/ -_)\\ \\/ _ \\/ -_) -_) _  / -_) __/\n" +
                "/_/  \\_, / .__/\\__/___/ .__/\\__/\\__/\\_,_/\\__/_/   \n" +
                "    /___/_/          /_/                          ");
    }

    public int getValidIntegerInput(int minValue, int maxValue){
        int userInput = 0;
        boolean isUserInputInvalid;

        do {
            isUserInputInvalid = false;
            try {
                userInput = scanner.nextInt();
                if (userInput < minValue || userInput > maxValue) {
                    System.out.println("Invalid entry, please enter a number between " + minValue + " and " + maxValue + "...");
                    isUserInputInvalid = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid entry, please enter a number between " + minValue + " and " + maxValue + "...");
                isUserInputInvalid = true;
            }
            scanner.nextLine();
        } while (isUserInputInvalid);

        return userInput;
    }

    @Override
    public boolean yesNo(String prompt) {
        System.out.print(prompt + " (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
    }

    @Override
    public String getString() {
        return scanner.nextLine();
    }

    @Override
    public void addString(String s) {
        System.out.println(s);
    }

    @Override
    public void clear() {}

    @Override
    public void exit() {
        System.out.println("Exiting the application.");
        System.exit(0);
    }
}
