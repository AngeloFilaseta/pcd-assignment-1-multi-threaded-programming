package jpf.controller.apps;

import controller.wordsCounter.PrintOnTerminal;
import jpf.controller.wordsCounter.WordsCounterJPF;

public class AppConcurrentJPF {
    private static final int nProcessorsAvailable = Runtime.getRuntime().availableProcessors();

    private static final String N_FREQUENT_WORDS = "10";
    private static final String DOCUMENT_PATH = "./src/main/resources/documents";
    private static final String IGNORED_WORDS = "./src/main/resources/documents/ignored-words.txt";

    public static void main(String[] args) {
        System.out.println("CLASS RUNNING: " + AppConcurrentJPF.class.getName());
        String[] hardCodedArgs = new String[3];
        hardCodedArgs[0] = (N_FREQUENT_WORDS);
        hardCodedArgs[1] = (IGNORED_WORDS);
        hardCodedArgs[2] = (DOCUMENT_PATH);
        try {
            WordsCounterJPF wordsCounter = new WordsCounterJPF(hardCodedArgs, () -> {});
            wordsCounter.execute(nProcessorsAvailable, new PrintOnTerminal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
