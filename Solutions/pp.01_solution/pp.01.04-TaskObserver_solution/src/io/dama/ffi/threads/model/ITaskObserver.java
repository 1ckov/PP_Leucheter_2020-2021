package io.dama.ffi.threads.model;

public interface ITaskObserver {
    public void inform(ITask changed);
}
