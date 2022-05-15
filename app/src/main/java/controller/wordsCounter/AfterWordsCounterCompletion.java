package controller.wordsCounter;

import model.sharedResources.SharedResources;
import controller.util.Chrono;

public interface AfterWordsCounterCompletion {

    void executeAfterCompletion(final SharedResources sharedResources, final Chrono chrono);

}
