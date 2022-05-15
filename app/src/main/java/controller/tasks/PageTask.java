package controller.tasks;

import model.page.Page;
import model.sharedResources.SharedResources;

import java.util.HashMap;
import java.util.Map;

public class PageTask implements Task {

    private final Page page;
    private final Map<String, Integer> localWordsCount;
    private final SharedResources sharedResources;

    public PageTask(final Page page, final SharedResources sharedResources) {
        this.page = page;
        this.localWordsCount = new HashMap<>();
        this.sharedResources = sharedResources;
    }

    @Override
    public void execute() {
        page.getWords().forEach(this::addToLocalWordsCount);
        sharedResources.getOrderedMap().putAll(localWordsCount);
    }

    private void addToLocalWordsCount(final String string){
        if(!sharedResources.getIgnoredWords().getWords().contains(string)){
            if(localWordsCount.containsKey(string)){
                localWordsCount.replace(string, localWordsCount.get(string) +1);
            } else {
                localWordsCount.put(string,1);
            }
        }
    }
}
