class UniversalTU {

    private Tape tape;
    private String[][] transitionFunction;

    public UniversalTU(int tapesize) {
        this.tape = new Tape(tapesize);
    }

    public boolean run(String[][] transitionFunction, String input) throws OutOfTapeException {
        this.transitionFunction = transitionFunction;
        String currentstate = "q1";

        writeInputToTape(input);

        while (true) {
            String symbolRead;
            try {
                symbolRead = tape.read();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OutOfTapeException();
            }

            String myfunc[] = getTransitionFunction(currentstate, symbolRead);
            if (myfunc == null) {
                return currentstate == "q2" ? true : false;
            }

            String symbolWrite = myfunc[3];
            String movement = myfunc[4];

            try {
                tape.write(symbolWrite);
                tape.step(movement);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OutOfTapeException();
            }

            currentstate = myfunc[2];;
        }

    }

    public Tape getTape() {
        return tape;
    }

    private String[] getTransitionFunction(String currentstate, String symbolRead) {
        for (String[] line : transitionFunction) {
            if (line[0].equals(currentstate) && line[1].equals(symbolRead)) {
                return line;
            }
        }
        return null;
    }

    private void writeInputToTape(String input) throws OutOfTapeException {
        for (int i = 0; i < input.length(); i++) {
            String character = String.valueOf(input.charAt(i));
            try {
                tape.write(character);
                tape.step("R");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OutOfTapeException();
            }
        }

        // reverse to tape beginning
        for (int i = 0; i < input.length(); i++) {
            tape.step("L");
        }
    }

}