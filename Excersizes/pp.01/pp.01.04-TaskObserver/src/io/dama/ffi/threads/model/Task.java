package io.dama.ffi.threads.model;

import io.dama.ffi.threads.run.synch.Runner;

public class Task implements ITask {

    private final String id;
    private volatile int nicelevel = 0;
    private volatile ITaskObserver[] observers;

    public Task(final String id) {
        this.id = id;
    }

    public static void main(final String[] args) {
        final var tasks = new Task[100];
        for (var i = 0; i < 100; i++) {
            tasks[i] = new Task(String.format("Task-%04d", i));
        }
        Runner.test(tasks);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getNicelevel() {
        return this.nicelevel;
    }

    @Override
    public void setNicelevel(final int nicelevel) {
        this.nicelevel=nicelevel;
        informAll();
    }

    @Override
    public void registerObserver(final ITaskObserver o) {
        int oldLenght = 0;
        if(observers != null){
            oldLenght = observers.length;
        }
        //If this is the first Observer to be registred then this
        if(oldLenght == 0) {
            observers = new ITaskObserver[1];
            observers[0] = o;
        }
        //If there are already Observers registered then this
        else {
            //Make new table with room for one more Observer
            ITaskObserver[] new_table = new ITaskObserver[oldLenght+1];
            //Put all the old Observers in the new table
            for(int i = 0; i < oldLenght; i++){
                new_table[i] = observers[i];
            }
            //Finally add the new one
            new_table[oldLenght] = o;
            //And replace the old table with the new One
            observers = new_table;
            //Finally we inform all that something has changed
            informAll();
        }


    }
    /** Inform all Observers that a change has happend in our task */
    private void informAll() {
        if(observers != null){
            for (ITaskObserver observer : observers){
                observer.inform(this);
            }
        }
    }

    @Override
    public void unregisterObserver(final ITaskObserver o) {
        int oldLength = 0;
        if(observers != null){
            oldLength = observers.length;
        }

        if(oldLength <= 0) {
            System.out.println("No observers to be removed");
        }
        else if (oldLength == 1) {
            observers = null;
        }
        else{
            ITaskObserver[] newTable = new ITaskObserver[oldLength-1];
            int removed = 0;
            for(int i = 0; i < oldLength - 1; i++){
                if(o.equals(observers[i])){
                    removed = 1;
                }
                newTable[i] = observers[i+removed];
            }
            observers=newTable;
            
            informAll();
        }

    }

}
