package jpf.model.documentsFolder.sharedResources.ignoredWords;

import model.sharedResources.ignoredWords.IgnoredWords;

import java.util.HashSet;
import java.util.Set;

public class IgnoredWordsJPF implements IgnoredWords {
    @Override
    public Set<String> getWords() {
        return new HashSet<>();
    }
}
