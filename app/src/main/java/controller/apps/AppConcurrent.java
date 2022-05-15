package controller.apps;

import controller.wordsCounter.PrintOnTerminal;
import controller.wordsCounter.WordsCounter;

public class AppConcurrent {

    private static final int nProcessorsAvailable = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        System.out.println("CLASS RUNNING: "+ AppConcurrent.class.getName());
        try {
            WordsCounter wordsCounter = new WordsCounter(args, () -> {
                //does nothing
            });
            wordsCounter.execute(nProcessorsAvailable, new PrintOnTerminal());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

}
