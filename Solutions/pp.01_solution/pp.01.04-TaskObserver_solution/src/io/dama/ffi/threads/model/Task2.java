package io.dama.ffi.threads.model;

import java.util.HashSet;
import java.util.Set;

import io.dama.ffi.threads.run.Runner;

public class Task2 implements ITask {

    private final String id;
    private int nicelevel = 0;
    private final Set<ITaskObserver> observers = new HashSet<>();

    public Task2(final String id) {
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
    public synchronized int getNicelevel() {
        return this.nicelevel;
    }

    @Override
    public synchronized void setNicelevel(final int nicelevel) {
        this.nicelevel = nicelevel;
        for (final var o : this.observers) {
            o.inform(this);
        }
    }

    public static void main(final String[] args) {
        final var tasks = new Task2[100];
        for (var i = 0; i < 100; i++) {
            tasks[i] = new Task2(String.format("Task-%04d", i));
        }
        Runner.test(tasks);
    }

}