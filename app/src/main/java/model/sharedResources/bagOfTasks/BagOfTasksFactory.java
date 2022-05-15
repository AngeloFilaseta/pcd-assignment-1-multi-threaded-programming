package model.sharedResources.bagOfTasks;

public class BagOfTasksFactory {

    public static BagOfTasks getBagOfTasks(){
        return new BagOfTasksImpl();
    }

}
