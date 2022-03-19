package Console;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner userInput = new Scanner(System.in);
    protected ConsoleOutput userOutput;

    public ConsoleInput(ConsoleOutput userOutput){
        this.userOutput = userOutput;
    }

    public String getString(){
        return userInput.nextLine();
    }

    protected int convertToInteger(String input){
        return Integer.parseInt(input);
    }

    public int getInteger(){
        boolean found = false;
        int result = -1;
        String input = null;
        do {
            try {
                input = getString();
                result = convertToInteger(input);
                found = true;
            } catch (NumberFormatException exception) {
                userOutput.output("Please enter a integer");
                //throw exception;
            }
        } while (!found);

        return result;
    }

    public void setUserInput(Scanner userInput){
        this.userInput = userInput;
    }
}
