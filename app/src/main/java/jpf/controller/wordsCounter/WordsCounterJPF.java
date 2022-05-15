package jpf.controller.wordsCounter;


import controller.tasks.Task;
import controller.util.Chrono;
import controller.wordsCounter.AfterWordsCounterCompletion;
import controller.workers.BagOfTasksWorkerFactory;
import model.sharedResources.SharedResources;
import model.sharedResources.SharedResourcesBuilder;
import model.sharedResources.argsContainer.ArgsContainer;
import model.sharedResources.argsContainer.ArgsContainerFactory;
import model.sharedResources.bagOfTasks.BagOfTasksFactory;
import model.sharedResources.orderedMap.OnMapPutStrategy;
import model.sharedResources.orderedMap.SyncWordCounterFactory;
import jpf.controller.tasks.ReadFileTaskJPF;
import jpf.model.documentsFolder.DocumentsFolderJPF;
import jpf.model.documentsFolder.sharedResources.ignoredWords.IgnoredWordsJPF;


import java.util.ArrayList;
import java.util.List;

public class WordsCounterJPF {

    private final SharedResources sharedResources;
    private final OnMapPutStrategy onMapPutStrategy;

    public WordsCounterJPF(final String[] args, final OnMapPutStrategy onMapPutStrategy) {
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

    private SharedResources createSharedResources(final String[] args) {
        ArgsContainer argsContainer = ArgsContainerFactory.getArgsContainer(args);
        return new SharedResourcesBuilder()
                    .setArgsContainer(argsContainer)
                    .setOrderedMap(SyncWordCounterFactory.getSyncWordCounter(onMapPutStrategy))
                    .setIgnoredWords(new IgnoredWordsJPF())
                    .setBagOfTasks(BagOfTasksFactory.getBagOfTasks())
                    .build();
    }

    private void populateBagOfTasks(){
        DocumentsFolderJPF documentsFolder = new DocumentsFolderJPF(sharedResources.getArgsContainer().getDocumentPath());
        List<Task> tasks = new ArrayList<>();
        documentsFolder.getPDFDocumentsName().forEach(d -> tasks.add(new ReadFileTaskJPF(sharedResources)));
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
