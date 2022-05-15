package model.sharedResources.argsContainer;

import model.sharedResources.argsContainer.ArgsContainer;

public class ArgsContainerImpl implements ArgsContainer {

    private final int n;
    private final String f;
    private final String d;

    protected ArgsContainerImpl(
            final int nMostFrequentWords,
            final String documentPath,
            final String ignoredWordsFileName
    ){
        n = nMostFrequentWords;
        f = ignoredWordsFileName;
        d = documentPath;
    }

    @Override
    public int getNMostFrequentWords() {
        return n;
    }

    @Override
    public String getDocumentPath() {
        return d;
    }

    @Override
    public String getIgnoredWordsFileName() {
        return f;
    }

}
