
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoedelNumberCalc {
    String inputForTM;
    String ausgabe;
    String[] binary = {"0", "00", "000"};

    Map<String, String> stateToBinary;
    Map<String, String> inputToBinary;
    Map<String, String> movementToBinary;
    Map<String, String> binaryToState;
    Map<String, String> binaryToInput;
    Map<String, String> binaryToMovement;
    String[][] transitionFunctionToGoedelNumber = {{"q20","0","q1","0","R"},
            {"q20","1","q17","0","R"},
            {"q1","0","q14","0","R"},
            {"q1","1","q17","0","R"},
            {"q17","0","q3","0","R"},
            {"q17","1","q17","1","R"},
            {"q3","0","q15","0","L"},
            {"q3","1","q4","0","R"},
            {"q4","0","q5","0","R"},
            {"q4","1","q4","1","R"},
            {"q5","0","q6","1","L"},
            {"q5","1","q5","1","R"},
            {"q6","0","q7","0","L"},
            {"q6","1","q6","1","L"},
            {"q7","0","q9","1","L"},
            {"q7","1","q8","1","L"},
            {"q8","0","q3","1","R"},
            {"q8","1","q8","1","L"},
            {"q9","0","q10","0","L"},
            {"q9","1","q9","1","L"},
            {"q10","0","q12","␣","R"},
            {"q10","1","q11","1","L"},
            {"q11","0","q20","␣","R"},
            {"q11","1","q11","1","L"},
            {"q12","0","q12","␣","R"},
            {"q12","1","q13","␣","R"},
            {"q13","0","q2","␣","S"},
            {"q13","1","q13","␣","R"},
            {"q14","0","q2","0","S"},
            {"q14","1","q14","0","R"},
            {"q15","0","q16","0","L"},
            {"q15","1","q15","0","L"},
            {"q16","0","q2","0","S"},
            {"q16","1","q16","0","L"},
            {"q4","␣","q5","0","R"},
            {"q5","␣","q6","1","L"},
            {"q6","␣","q7","0","L"},
            {"q11","␣","q20","0","R"}};


    public GoedelNumberCalc(String[] characters, String[] states) {
        stateToBinary = stateToBinary(states);
        inputToBinary = inputToBinary(characters);
        movementToBinary = Map.of("L", binary[0], "R", binary[1], "S", binary[2]);
        System.out.println(movementToBinary);
        binaryToState = binaryToState(states);
        binaryToInput = binaryToInput(characters);
        binaryToMovement = Map.of(binary[0], "L", binary[1], "R", binary[2], "S");
        transitionFunctionToGoedelNumber();
    }

    // Define the encoding for the states, input alphabet, tape alphabet, and movements

    // If you find three "1" in a row you know the next number is the input. after these 3 we split
    public String GoedelNumberSplitFromInput(String input){
        String[] parts = input.split("111", 2);
        String goedelNumberWithoutInput = parts[0];
        try {
            inputForTM = parts[1];
        } catch (Exception e) {
            System.out.println("");
        }
        return goedelNumberWithoutInput;
    }

    public String getInputForTM() {
        return inputForTM;
    }

    public void transitionFunctionToGoedelNumber() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i<transitionFunctionToGoedelNumber.length; i++) {
            transitionFunctionToGoedelNumber[i][0] = stateToBinary.get(transitionFunctionToGoedelNumber[i][0]) + "1";
            transitionFunctionToGoedelNumber[i][1] = inputToBinary.get(transitionFunctionToGoedelNumber[i][1]) + "1";
            transitionFunctionToGoedelNumber[i][2] = stateToBinary.get(transitionFunctionToGoedelNumber[i][2]) + "1";
            transitionFunctionToGoedelNumber[i][3] = inputToBinary.get(transitionFunctionToGoedelNumber[i][3]) + "1";
            transitionFunctionToGoedelNumber[i][4] = movementToBinary.get(transitionFunctionToGoedelNumber[i][4]) + "11";
            for (String element : transitionFunctionToGoedelNumber[i]) {
                sb.append(element);
            }
        }

        ausgabe = sb.toString();
        System.out.println("A" + ausgabe);

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
                if (elements.length != 5) {
                    System.out.println("Error: Function " + i + " has " + elements.length + " elements");
                    return null;
                }
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

            String goedelNumberWithout1;
            if (goedelNumber.contains("2") || goedelNumber.contains("3") || goedelNumber.contains("4") || goedelNumber.contains("5") || goedelNumber.contains("6") || goedelNumber.contains("7") || goedelNumber.contains("8") || goedelNumber.contains("9")) {
                goedelNumber = decimalStringToBinaryString(goedelNumber);
            }
            if (goedelNumber.startsWith("1")){
                goedelNumberWithout1 = goedelNumber.substring(1);
            }
            else {
                goedelNumberWithout1 = goedelNumber;
            }
            String goedelNumberWithoutInput = GoedelNumberSplitFromInput(goedelNumberWithout1);
            String[][] transitionFunction3 = goedelNumberToTransitionFunction(goedelNumberWithoutInput);
            return transitionFunction3;

    }

    public String decimalStringToBinaryString(String decimalString) {
        BigInteger decimalNumber = new BigInteger(decimalString);
        String binaryString = decimalNumber.toString(2);
        System.out.println("Binary: " + binaryString);
        return binaryString;
    }

    public String readGoedelNumberFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/GoedelNumber"));
            return String.join("", lines);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public String[][] getTransitionFunctionToGoedelNumber() {
        return transitionFunctionToGoedelNumber;
    }

}

