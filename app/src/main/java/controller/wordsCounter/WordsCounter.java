package controller.wordsCounter;

import controller.tasks.ReadFileTask;
import controller.tasks.Task;
import model.sharedResources.SharedResources;
import model.sharedResources.SharedResourcesBuilder;
import model.sharedResources.argsContainer.ArgsContainer;
import model.sharedResources.argsContainer.ArgsContainerFactory;
import model.documentsFolder.DocumentsFolderFactory;
import model.sharedResources.ignoredWords.IgnoredWordsFactory;
import model.sharedResources.orderedMap.OnMapPutStrategy;
import model.sharedResources.orderedMap.SyncWordCounterFactory;
import model.sharedResources.bagOfTasks.BagOfTasksFactory;
import controller.workers.BagOfTasksWorkerFactory;
import model.documentsFolder.DocumentsFolder;
import controller.util.Chrono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordsCounter {

    private final SharedResources sharedResources;
    private final OnMapPutStrategy onMapPutStrategy;

    public WordsCounter(final String[] args, final OnMapPutStrategy onMapPutStrategy) throws Exception{
        this.onMapPutStrategy = onMapPutStrategy;
        this.sharedResources = createSharedResources(args);
    }

    public SharedResources getSharedResources(){
        return sharedResources;
    }

    public void execute(
            final int nDedicatedThreads,
            final AfterWordsCounterCompletion afterWordsCounterCompletion){
        Chrono chrono = new Chrono();
        chrono.start();
        populateBagOfTasks();
        List<Thread> dedicatedWorkers = startThreads(sharedResources, nDedicatedThreads);
        joinThreads(dedicatedWorkers);
        chrono.stop();
        afterWordsCounterCompletion.executeAfterCompletion(sharedResources, chrono);
    }

    private SharedResources createSharedResources(final String[] args) throws IOException {
        ArgsContainer argsContainer = ArgsContainerFactory.getArgsContainer(args);
        return new SharedResourcesBuilder()
                    .setArgsContainer(argsContainer)
                    .setIgnoredWords(IgnoredWordsFactory.getIgnoredWords(argsContainer.getIgnoredWordsFileName()))
                    .setOrderedMap(SyncWordCounterFactory.getSyncWordCounter(onMapPutStrategy))
                    .setBagOfTasks(BagOfTasksFactory.getBagOfTasks())
                    .build();
    }

    private void populateBagOfTasks(){
        DocumentsFolder documentsFolder = DocumentsFolderFactory.getDocumentsFolder(sharedResources.getArgsContainer().getDocumentPath());
        List<Task> tasks = new ArrayList<>();
        for (String pdfDocumentName : documentsFolder.getPDFDocumentsName()) {
            tasks.add(new ReadFileTask(pdfDocumentName, sharedResources));
        }
        sharedResources.getBagOfTasks().addMultipleTasks(tasks);
    }

    private List<Thread> startThreads(final SharedResources sharedResources,
                                      final int nDedicatedThreads){
        List<Thread> dedicatedWorkers = new ArrayList<>();
        for (int i = 0; i < nDedicatedThreads; i++) {
            Thread simpleWorkerThread = new Thread(BagOfTasksWorkerFactory.createBagOfTasksWorker(sharedResources));
            dedicatedWorkers.add(simpleWorkerThread);
            simpleWorkerThread.start();
        }
        return dedicatedWorkers;
    }

    private void joinThreads(final List<Thread> dedicatedWorkers){
        while(!dedicatedWorkers.isEmpty()){
            try {
                dedicatedWorkers.remove(0).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
