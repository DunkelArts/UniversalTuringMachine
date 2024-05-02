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
        String[] characters = {"0", "1", "␣", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] states = {"q1", "q2", "q3", "q4","q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14", "q15", "q16", "q17", "q18", "q19", "q20", "q21", "q22", "q23", "q24", "q25", "q26", "q27", "q28", "q29", "q30", "q31", "q32", "q33", "q34", "q35", "q36", "q37", "q38", "q39", "q40", "q41", "q42", "q43", "q44", "q45", "q46", "q47", "q48", "q49", "q50", "q51", "q52", "q53", "q54", "q55", "q56", "q57", "q58", "q59", "q60", "q61", "q62", "q63", "q64", "q65", "q66", "q67", "q68", "q69", "q70", "q71", "q72", "q73", "q74", "q75", "q76", "q77", "q78", "q79", "q80", "q81", "q82", "q83", "q84", "q85", "q86", "q87", "q88", "q89", "q90", "q91", "q92", "q93", "q94", "q95", "q96", "q97", "q98", "q99", "q100"};
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

        universalTU.setStepmode(stepmode.equals("y"));

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
//            System.out.println("");
//            System.out.println("--- tape content after computation ---");
//            System.out.println(universalTU.getTape().printTape());
//            System.out.println(universalTU.getTape().getPosition());
//            System.out.println("Number of Steps: " + universalTU.getStepCounter());
            }
        } catch (OutOfTapeException e) {
            System.out.println("Not enough tape for this computation");
        }
    }
    }
