package jpf.model.util;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SentenceGenerator {

    private static final String SPACER = " ";
    public static String getSentences(final int numberOfSentences) {
        Random generator = new Random();
        StringBuilder buffer = new StringBuilder();

        final List<String> article = Arrays.asList("the", "a", "one", "some", "any");
        final List<String> noun = Arrays.asList("boy","dog","car","bicycle");
        final List<String> verb = Arrays.asList("ran","jumped","sang","moves");
        final List<String> preposition = Arrays.asList("away","towards","around","near");

        for(int j = 0; j < numberOfSentences; j++){
            int firstArticleIndex = generator.nextInt(article.size());
            int firstNounIndex = generator.nextInt(noun.size());
            int verbIndex = generator.nextInt(verb.size());
            int prepositionIndex = generator.nextInt(preposition.size());
            int secondArticleIndex = generator.nextInt(article.size());
            int secondNounIndex = generator.nextInt(noun.size());

            buffer.append(article.get(firstArticleIndex))
                    .append(SPACER)
                    .append(noun.get(firstNounIndex))
                    .append(SPACER)
                    .append(verb.get(verbIndex))
                    .append(SPACER)
                    .append(preposition.get(prepositionIndex))
                    .append(SPACER)
                    .append(article.get(secondArticleIndex))
                    .append(SPACER)
                    .append(noun.get(secondNounIndex))
                    .append( ".\n");
        }
        return buffer.toString();
    }
}
