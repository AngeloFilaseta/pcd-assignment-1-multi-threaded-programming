package controller.workers;

import model.sharedResources.SharedResources;
import model.sharedResources.bagOfTasks.BagOfTasks;
import controller.tasks.Task;

import java.util.Optional;

public class BagOfTasksWorker implements Worker {

    private final BagOfTasks bagOfTasks;

    protected BagOfTasksWorker(final SharedResources sharedResources){
        this.bagOfTasks = sharedResources.getBagOfTasks();
    }

    @Override
    public void run() {
        Optional<Task> task;
        do{
            task = bagOfTasks.getTask();
            task.ifPresent(Task::execute);
        }while(task.isPresent());
    }

}
