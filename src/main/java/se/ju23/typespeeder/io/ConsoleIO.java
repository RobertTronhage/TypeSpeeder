package se.ju23.typespeeder.io;
import java.util.Scanner;

public class ConsoleIO implements IO{
    private Scanner scanner;

    public ConsoleIO(){
        this.scanner = new Scanner(System.in);
    }

    public String introText(){
        return " ______              ____                __       \n" +
                "/_  __/_ _____  ___ / __/__  ___ ___ ___/ /__ ____\n" +
                " / / / // / _ \\/ -_)\\ \\/ _ \\/ -_) -_) _  / -_) __/\n" +
                "/_/  \\_, / .__/\\__/___/ .__/\\__/\\__/\\_,_/\\__/_/   \n" +
                "    /___/_/          /_/                          ";
    }

    @Override
    public boolean yesNo(String prompt) {
        System.out.print(prompt + " (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
    }

    @Override
    public String getString() {
        System.out.print("Enter a value: ");
        return scanner.nextLine();
    }

    @Override
    public void addString(String s) {
        System.out.println("Added string: " + s);
    }

    @Override
    public void clear() {}

    @Override
    public void exit() {
        System.out.println("Exiting the application.");
        System.exit(0);
    }
}
