import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

class UniversalTU {

    private Tape tape;
    private String[][] transitionFunction;
    private int stepCounter;
    private boolean stepMode;

    public UniversalTU(int tapesize) {
        this.tape = new Tape(tapesize);
        this.stepCounter = 0;
    }

    public boolean run(String[][] transitionFunction, String input) throws OutOfTapeException, InterruptedException {
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
            if(stepMode){
                System.out.println("Current State: " + currentstate);
                System.out.println("Step: " + stepCounter);
                System.out.println(tape.printTape());
                System.out.println(tape.getPosition());
                TimeUnit.SECONDS.sleep(1);
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
            stepCounter++;
            currentstate = myfunc[2];
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
    public int getStepCounter() {
        return stepCounter;
    }
    public void setStepmode(boolean stepmode) {
        this.stepMode = stepmode;
    }

}