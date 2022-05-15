package model.sharedResources.bagOfTasks;

import controller.tasks.Task;
import java.util.List;
import java.util.Optional;

public interface BagOfTasks {

    Optional<Task> getTask();

    void addMultipleTasks(final List<Task> tasks);

    /**
     * When pause is called, the task queue stops returning the tasks, causing all threads to pause.
     */
    void pause();

    /**
     * When resume is called, EVERY thread in wait will be awaken.
     */
    void resume();

    /**
     * @return if the tasks queue has been paused or not.
     */
    boolean isPaused();
}
