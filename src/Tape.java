public class Tape {
    private int position;
    private String[] tape;


    public Tape(int size) {
        this.tape = new String[size];
        
        //set Start position in middle of tape
        this.position = (size-1)/2;

        //initialize Tape with Blank
        for (int i = 0; i < tape.length; i++) {
            tape[i] = "␣";
        }
    }

    public void step(String n) {
        if(n == "R") {
            position++;
        }

        if(n == "L") {
            position--;
        }
    }

    public String read() {
        return tape[position];
    }

    public void write(String n) {
        tape[position] = n;
    }

    public String printTape() {
        StringBuilder sb = new StringBuilder();
        for(String str : tape) {
            sb.append(str);
        }
        return sb.toString();
    }
}
