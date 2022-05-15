package model.sharedResources.ignoredWords;


import java.io.IOException;

public class IgnoredWordsFactory {

    public static IgnoredWords getIgnoredWords(final String ignoredWordsPath) throws IOException {
        return new IgnoredWordsImpl(ignoredWordsPath);
    }

}
