package model.sharedResources.bagOfTasks;

import controller.tasks.Task;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BagOfTasksImpl implements BagOfTasks {

    private final List<Task> tasksQueue;
    private final Lock mutex;
    private final Condition isAvailable;
    private boolean paused;

    protected BagOfTasksImpl(){
        this.mutex = new ReentrantLock();
        this.isAvailable = mutex.newCondition();
        this.tasksQueue = new ArrayList<>();
        this.paused = false;
    }

    private void waitInGetIfNeeded(){
        while(paused){
            try {
                isAvailable.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Task> getTask() {
        try{
            mutex.lock();
            waitInGetIfNeeded();
            if(tasksQueue.isEmpty()){
                return Optional.empty();
            } else {
                Task result = tasksQueue.remove(0);
                return Optional.of(result);
            }
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void addMultipleTasks(List<Task> tasks) {
        try{
            mutex.lock();
            tasksQueue.addAll(tasks);
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void pause() {
        try {
            mutex.lock();
            paused = true;
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void resume() {
        try {
            mutex.lock();
            paused = false;
            isAvailable.signalAll();
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public boolean isPaused() {
        try {
            mutex.lock();
            return paused;
        } finally {
            mutex.unlock();
        }
    }

}
