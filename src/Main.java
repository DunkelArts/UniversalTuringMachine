import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        UniversalTU universalTU = new UniversalTU(100);

        // Define the characters and states
        String[] characters = {"0", "1", "␣", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[] states = {"q1", "q2", "q3", "q4"};

        // Define the Goedel number of the Turing machine starting with zero 111 then input
        String goedelNumber = "0101000101001101001010010011000101000010100110001001010010011000010100001010011000010010000100100110000100010010001001111111010101";

        // Create a GoedelNumberCalc object with the Characters and States
        GoedelNumberCalc goedelNumberCalc = new GoedelNumberCalc(characters, states);

        // Split the Goedel number from the input
        String[][] transitionFunction = goedelNumberCalc.getTransitionFunction(goedelNumber);
        System.out.println(Arrays.deepToString(transitionFunction));

        // Get the input for the Turing Machine
        String input = goedelNumberCalc.getInputForTM();
        System.out.println("input: " + input);

        try {
            boolean bingo = universalTU.run(transitionFunction, input);
            if(bingo) {
                System.out.println("this Turing Machine accepts");
            } else {
                System.out.println("this Turing Machine does not accept");
            }
            System.out.println("--- tape content after computation ---");
            System.out.println(universalTU.getTape().printTape());
        } catch (OutOfTapeException e) {
            System.out.println("Not enough tape for this computation");
        }
    }
}
