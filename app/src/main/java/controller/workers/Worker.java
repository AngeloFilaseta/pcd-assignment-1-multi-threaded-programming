package controller.workers;

import model.sharedResources.bagOfTasks.BagOfTasks;
import controller.tasks.Task;

/**
 * A Worker executes Tasks retrieving them from a TaskQueueMonitor.
 * @see Task
 * @see BagOfTasks
 */
public interface Worker extends Runnable {

}
