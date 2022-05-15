package controller.workers;

import model.sharedResources.SharedResources;

public class BagOfTasksWorkerFactory {

    public static Worker createBagOfTasksWorker(final SharedResources sharedResources){
        return new BagOfTasksWorker(sharedResources);
    }

}
