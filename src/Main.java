import java.util.Arrays;
import java.util.Scanner;
//01010001010011010010100100110001010000101001100010010100100110000101000010100110000100100001001001100001000100100010011111110101001
//185943403774763668
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        UniversalTU universalTU = new UniversalTU(100);
        String goedelNumber = "";
        // Define the characters and states
        String[] characters = {"0", "1", "␣", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[] states = {"q1", "q2", "q3", "q4"};
        GoedelNumberCalc goedelNumberCalc = new GoedelNumberCalc(characters, states);
        // Define the Goedel number of the Turing machine starting with zero-Goedel number-111-Input or as decimal
        System.out.println("Read Goedel number from file? (y/n)");
        String readFromFile = scanner.nextLine();
        if (readFromFile.equals("y")) {
            goedelNumber = goedelNumberCalc.readGoedelNumberFromFile();
        }
        else {
            System.out.println("Please enter a Goedel number for the Turing Machine: ");
            goedelNumber = scanner.nextLine();
        }

        System.out.println("Stepmode? (y/n)");
        String stepmode = scanner.nextLine();

        if (stepmode.equals("y")) {
            universalTU.setStepmode(true);
        }
        else{
            universalTU.setStepmode(false);
        }

        //String goedelNumber = "01010001010011010010100100110001010000101001100010010100100110000101000010100110000100100001001001100001000100100010011111110101001";

        // Create a GoedelNumberCalc object with the Characters and States


        // Get the transition function from the Goedel number
        String[][] transitionFunction;
        transitionFunction = goedelNumberCalc.getTransitionFunction(goedelNumber);
        System.out.println(Arrays.deepToString(transitionFunction));
        // Get the input for the Turing Machine
        String input = goedelNumberCalc.getInputForTM();
        System.out.println("input: " + input);

        try {
            if (input == null || transitionFunction == null) {
                System.out.println("No input or transition function found. Turing Machine cannot run.");
            }
            else {
                boolean bingo = universalTU.run(transitionFunction, input);
                if (bingo) {
                    System.out.println("this Turing Machine accepts");
                } else {
                    System.out.println("this Turing Machine does not accept");
                }
            System.out.println("");
            System.out.println("--- tape content after computation ---");
            System.out.println(universalTU.getTape().printTape());
            System.out.println(universalTU.getTape().getPosition());
            System.out.println("Number of Steps: " + universalTU.getStepCounter());
            }
        } catch (OutOfTapeException e) {
            System.out.println("Not enough tape for this computation");
        }
    }
    }
