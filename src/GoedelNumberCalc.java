
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GoedelNumberCalc {
    String inputForTM;
    String ausgabe = new String();
    String[] binary = {"0", "00"};

    Map<String, String> stateToBinary;
    Map<String, String> inputToBinary;
    Map<String, String> movementToBinary;
    Map<String, String> binaryToState;
    Map<String, String> binaryToInput;
    Map<String, String> binaryToMovement;
    String[][] transitionFunctionToGoedelNumber = {
            {"q1", "0", "q2", "0", "R"},
            {"q2", "0", "q2", "0", "R"},
            {"q2", "1", "q3", "1", "R"},
            {"q3", "0", "q4", "0", "R"},
            {"q4", "0", "q4", "0", "R"},
            {"q4", "1", "q1", "1", "R"}
    };


    public GoedelNumberCalc(String[] characters, String[] states) {
        stateToBinary = stateToBinary(states);
        inputToBinary = inputToBinary(characters);
        movementToBinary = Map.of("L", binary[0], "R", binary[1]);
        binaryToState = binaryToState(states);
        binaryToInput = binaryToInput(characters);
        binaryToMovement = Map.of(binary[0], "L", binary[1], "R");
    }

    // Define the encoding for the states, input alphabet, tape alphabet, and movements

    // If you find three "1" in a row you know the next number is the input. after these 3 we split
    public String GoedelNumberSplitFromInput(String input){
        String[] parts = input.split("111", 2);
        String goedelNumberWithoutInput = parts[0];
        try {
            inputForTM = parts[1];
        } catch (Exception e) {
            System.out.println("iwi wird eine exception gecatcht, code wird aber trotzdem ausgeführt und input geschrieben");
        }
        return goedelNumberWithoutInput;
    }

    public String getInputForTM() {
        return inputForTM;
    }

    public void transitionFunctionToGoedelNumber() {
        for (String[] function : transitionFunctionToGoedelNumber) {
            StringBuilder sb = new StringBuilder();

            function[0] = stateToBinary.get(function[0]);
            function[1] = inputToBinary.get(function[1]);
            function[2] = stateToBinary.get(function[2]);
            function[3] = inputToBinary.get(function[3]);
            function[4] = movementToBinary.get(function[4]);
            sb = sb.append(function[0] + "1").append(function[1] + "1").append(function[2] + "1").append(function[3] + "1").append(function[4] + "11");
            ausgabe = ausgabe + sb.toString();

        }
        System.out.println(ausgabe);
    }

    public String[][] goedelNumberToTransitionFunction(String goedelNumberWithoutInput) {
        String[][] transitionFunction2;
        String temp = GoedelNumberSplitFromInput(goedelNumberWithoutInput);

        String[] functions = temp.split("11");

        // Create a new transition function array
        transitionFunction2 = new String[functions.length][5];

        // Iterate over each function
        for (int i = 0; i < functions.length; i++) {
            // Split the function into elements
            String[] elements = functions[i].split("1");

            // Map the binary strings back to their original values
            transitionFunction2[i][0] = binaryToState.get(elements[0]);
            transitionFunction2[i][1] = binaryToInput.get(elements[1]);
            transitionFunction2[i][2] = binaryToState.get(elements[2]);
            transitionFunction2[i][3] = binaryToInput.get(elements[3]);
            transitionFunction2[i][4] = binaryToMovement.get(elements[4]);
        }
        return transitionFunction2;

    }

    public Map<String, String> inputToBinary(String[] characters){
        Map<String, String> binaryStrings = new HashMap<>();
        String binary[] = generateZeroToUnlimitedZeroCounterStrings(characters.length);
        for (int i = 0; i < characters.length; i++) {
            binaryStrings.put(characters[i], binary[i]);
        }
        return binaryStrings;
    }

    public Map<String, String> binaryToInput(String[] characters){
        Map<String, String> binaryStrings = new HashMap<>();
        String binary[] = generateZeroToUnlimitedZeroCounterStrings(characters.length);
        for (int i = 0; i < characters.length; i++) {
            binaryStrings.put(binary[i], characters[i]);
        }
        return binaryStrings;
    }

    public Map<String, String> stateToBinary(String[] states){
        Map<String, String> binaryStrings = new HashMap<>();
        String binary[] = generateZeroToUnlimitedZeroCounterStrings(states.length);
        for (int i = 0; i < states.length; i++) {
            binaryStrings.put(states[i], binary[i]);
        }
        return binaryStrings;
    }

    public Map<String, String> binaryToState(String[] states){
        Map<String, String> binaryStrings = new HashMap<>();
        String binary[] = generateZeroToUnlimitedZeroCounterStrings(states.length);
        for (int i = 0; i < states.length; i++) {
            binaryStrings.put(binary[i], states[i]);
        }
        return binaryStrings;
    }

    private String[] generateZeroToUnlimitedZeroCounterStrings(int length){
        String[] zeroCounterStrings = new String[length];

        for (int i = 0; i < length; i++) {
            zeroCounterStrings[i] = "0" + "0".repeat(i);
            zeroCounterStrings[0] = "0";
        }
        return zeroCounterStrings;
    }

    public String[][] getTransitionFunction(String goedelNumber) {
        String goedelNumberWithoutInput = GoedelNumberSplitFromInput(goedelNumber);
        String[][] transitionFunction3 = goedelNumberToTransitionFunction(goedelNumberWithoutInput);
        return transitionFunction3;

    }


}
