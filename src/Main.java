public class Main {
    public static void main(String[] args) {
        UniversalTU universalTU = new UniversalTU(100);
        
        // Accept for L = {*00* | {0,1}*}
        String input = "1010101000";
        String[][] transitionFunction = {
            {"q1", "1", "q1", "1", "R"},
            {"q1", "0", "q3", "0", "R"},
            {"q3", "1", "q1", "1", "R"},
            {"q3", "0", "q4", "0", "R"},
            {"q4", "0", "q4", "0", "R"},
            {"q4", "1", "q4", "1", "R"},
            {"q4", "␣", "q2", "␣", "R"}      
        };

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
