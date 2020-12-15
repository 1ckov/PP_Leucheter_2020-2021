package io.dama.ffi.threads.model;

import java.util.HashSet;
import java.util.Set;

import io.dama.ffi.threads.run.synch.Runner;

public class Task4 implements ITask {

    private final String id;
    private int nicelevel = 0;
    private final Set<ITaskObserver> observers = new HashSet<>();

    public Task4(final String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public synchronized void registerObserver(final ITaskObserver o) {
        if (o != null) {
            this.observers.add(o);
        }
    }

    @Override
    public synchronized void unregisterObserver(final ITaskObserver o) {
        if (o != null) {
            this.observers.remove(o);
        }
    }

    @Override
    public int getNicelevel() {
        return this.nicelevel;
    }

    @Override
    public void setNicelevel(final int nicelevel) {
        Set<ITaskObserver> observersCopy;
        synchronized (this) {
            observersCopy = new HashSet<>(this.observers);
            this.nicelevel = nicelevel;
        }
        for (final var o : observersCopy) {
            o.inform(this);
        }
    }

    public static void main(final String[] args) {
        final ITask[] tasks = new Task4[100];
        for (int i = 0; i < 100; i++) {
            tasks[i] = new Task4(String.format("Task-%04d", i));
        }
        Runner.test(tasks);
    }

}
