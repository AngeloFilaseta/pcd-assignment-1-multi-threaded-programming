package model.sharedResources.ignoredWords;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class IgnoredWordsImpl implements IgnoredWords {

    private final Set<String> ignoredWords;

    protected IgnoredWordsImpl(final String ignoredWordsPath) throws IOException {
        ignoredWords = getSetOfWords(new File(ignoredWordsPath));
    }

    @Override
    public Set<String> getWords() {
        return ignoredWords;
    }

    private Set<String> getSetOfWords(final File ignoredWordsFile) throws IOException {
        Set<String> words = new HashSet<>();
        final FileInputStream inputStream = new FileInputStream(ignoredWordsFile);
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8.toString());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(inputStreamReader);
            reader.lines().map(String::trim).forEach(words::add);
        }finally {
            if(reader != null){
                reader.close();
            }
        }
        return words;
    }

}
