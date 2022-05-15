package controller.apps;

import controller.wordsCounter.PrintOnTerminal;
import controller.wordsCounter.WordsCounter;

public class AppSequential {

    public static void main(String[] args) {
        System.out.println("CLASS RUNNING: "+ AppSequential.class.getName());
        try {
            WordsCounter wordsCounter = new WordsCounter(args, () -> {
                //does nothing
            });
            wordsCounter.execute(1, new PrintOnTerminal());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

}
