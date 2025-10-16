
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ed T
 */
public class main {

    /**
     * this is the start up method and is primarily used to just start up the primaryFunction()
     * @param argc
     */
    public static void main(String[] argc){

        primaryFunction();

    }

    /**
     * Primary function used to compile and parse inputs
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public static void primaryFunction(){

        // variables
        String preParse;

        // imports
        Scanner keyboard = new Scanner(System.in);

        // grabbing line to be parsed
        preParse = keyboard.nextLine();
        keyboard.close(); // closes keyboard when done with entering line

        // Seperates strings so we can parse them
        ArrayList<String> seperatedStrings = seperateString(preParse.replaceAll(" ", ""));

        // formats the seperatedStrings into values that we can use
        toPrint(seperatedStrings);


    }

    //---------------------------------Setting Up Strings-------------------------------------

    /**
     * Seperates the string into characters
     * then will sort the characters and make them into individual strings that we can use
     * @param preParse
     * @return
     */
    public static ArrayList<String> seperateString(String preParse){

        // variables
        ArrayList<Character> seperatedChars = new ArrayList<>();
        ArrayList<String> returnValues = new ArrayList<>();
        String tempString = "";

        // seperates the string into characters
        for(int i = 0; i < preParse.length(); i++){
            seperatedChars.add(preParse.charAt(i));
        }
        
        

        // loops through char values and sorts them into bunches
        for(int i = 0; i < seperatedChars.size(); i++){

            // checks if the character is a letter
            if(Character.isLetter(seperatedChars.get(i))){
                tempString = tempString + seperatedChars.get(i);

                // makes sure that we don't go over the string size
                if(i == seperatedChars.size() - 1){
                    returnValues.add(tempString);
                    tempString = "";
                }
                // this makes it so it can bunch letters together to make words
                else if(!Character.isLetter(seperatedChars.get(i + 1))){
                    returnValues.add(tempString);
                    tempString = "";
                }
            }

            // checks to see if the character is a number
            else if(Character.isDigit(seperatedChars.get(i)) && Character.digit(seperatedChars.get(i), 10) != -1){
                tempString = tempString + seperatedChars.get(i);

                // makes sure that we don't go out of bounds
                if(i == seperatedChars.size() - 1){
                    returnValues.add(tempString);
                    tempString = "";
                }

                // makes it so we can make numbers over 9
                else if(!Character.isDigit(seperatedChars.get(i + 1))){
                    returnValues.add(tempString);
                    tempString = "";
                }
            }

            // catches the remainder and bunches them together
            else{
                tempString = tempString + seperatedChars.get(i);

                // makes sure that we don't go out of bounds
                if(i == seperatedChars.size() - 1){
                    returnValues.add(tempString);
                    tempString = "";
                }

                // makes sure the (, ), and ; are keep seperate then the rest
                else if(specialChars(seperatedChars.get(i + 1))){
                    returnValues.add(tempString);
                    tempString = "";
                }

                // bunches up the other characters together to use things like ++ and >=
                else if(Character.isDigit(seperatedChars.get(i + 1)) || Character.isLetter(seperatedChars.get(i + 1))){
                    returnValues.add(tempString);
                    tempString = "";
                }
            }
        }

        return returnValues;
    }

    /**
     * this makes sure that (, ), and ; are specialy identified
     * this is used in seperateString(String x)
     * @param holder
     * @return
     */
    public static boolean specialChars(Character holder){
        return switch (holder){
            case '(' -> true;
            case ')' -> true;
            case ';' -> true;
            default -> false;
        }; //ngl just learned that you set up switch/case like this; kind of like it
    }

    //------------------------------------Comipler/Token Maker-----------------------------

    /**
     * Prints out the values to show results
     * @param values
     */
    public static void toPrint(ArrayList<String> values){
        
        // variables
        ArrayList<String> tokens = valueCheck(values);

        // prints out list with lexeme and tokens
        System.out.println("Lexeme | tokens:");
        for(int i = 0; i < values.size(); i++){
            System.out.printf("%s  | %s\n", values.get(i), tokens.get(i));
        }

    }

    /**
     * Assigns tokens to lexemes that have been inputed
     * @param values
     * @return
     */
    public static ArrayList<String> valueCheck(ArrayList<String> values){

        // variables
        ArrayList<String> identifiers = new ArrayList<>();

        // sorting method
        for(int i = 0; i < values.size(); i++){

            // checks to see it's a letter
            if(letterCheck(values.get(i))){
                identifiers.add("Variable Name");
            }

            // checks to see if it's a number
            else if(checkInteger(values.get(i))){ // had to use a seperate method other it would break the code
                identifiers.add("Integer Value");
            }

            // catches anything else and throws it through a switch case to match everything up
            else{
                identifiers.add(operators(values.get(i)));
            }
        }

        // return value
        return identifiers;
    }

    /**
     * Compares strings to letters to see if the string is just a letter or a full string
     * swaps the value to lowercase but will not impact the program outside of this method
     * @param x
     * @return
     */
    public static boolean letterCheck(String x){
        String switchCaseValue = x.toLowerCase();
        return switch (switchCaseValue){
            case "a" -> true;
            case "b" -> true;
            case "c" -> true;
            case "d" -> true;
            case "e" -> true;
            case "f" -> true;
            case "g" -> true;
            case "h" -> true;
            case "i" -> true;
            case "j" -> true;
            case "k" -> true;
            case "l" -> true;
            case "m" -> true;
            case "n" -> true;
            case "o" -> true;
            case "p" -> true;
            case "q" -> true;
            case "r" -> true;
            case "s" -> true;
            case "t" -> true;
            case "u" -> true;
            case "v" -> true;
            case "w" -> true;
            case "x" -> true;
            case "y" -> true;
            case "z" -> true;
            default -> false;
        };
    }

    /**
     * checks to see if the string is an integer
     * this was required to be it's own method otherwise it would crash the program
     * @param x
     * @return
     */
    public static boolean checkInteger(String x){
        try {
            return Integer.parseInt(x) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This is where anything that isn't a letter or an integer goes
     * this sorts everything accordingly and then returns the value
     * @param x
     * @return
     */
    public static String operators(String x){
        return switch (x){
            case "+" -> "Addition Operator";
            case "-" -> "Subtraction Operator";
            case "*" -> "Multiplcation Operator";
            case "%" -> "Mod Operator";
            case "/" -> "Division Operator";
            case "^" -> "To The Power of - Exponent Operator";
            case "=" -> "Equals Sign - Comparison Operator";
            case "<" -> "Less Then - Comparison Operator";
            case ">" -> "Greater Then - Comparison Operator";
            case "==" -> "Equal To - Comparison Operator";
            case "!=" -> "Not Equal To - Comparion Operator";
            case "<=" -> "Less Then or Equal To - Comparison Operator";
            case ">=" -> "Greater Then or Equal To - Comparison Operator";
            case "(" -> "Left Parenthesis";
            case ")" -> "Right Parenthesis";
            case ";" -> "Semicolon - End of Instructions";
            default -> "Identifier";
        };
    }

    //----------------------------------MISC----------------------------------------

    @SuppressWarnings("ConvertToTryWithResources")
    public static void repeat(){
        // functions
        Scanner keyboard = new Scanner(System.in);

        // ask user
        System.out.print("\nDo you wish to use again? Y/N: ");
        String response = keyboard.next();
        keyboard.close();

        // process response
        if(response.compareTo("Y")==0 || response.compareTo("y")==0){
            primaryFunction(); // restarts loop
        }
        else if(response.compareTo("N")==0 || response.compareTo("n")==0){
            System.out.println("Thank you for your service");
            System.exit(0); // ends program
        }
        else{
            System.out.println("Improper Response; Please respond with a Y or a N");
        }
    }
}

/*
 * Test Values that can be entered to see if the code works
 * 
 * - input >= 17 * (count +2);
 * - 22^3 > 17 -2;
 * - input == a + b - 3;
 * 
 */
