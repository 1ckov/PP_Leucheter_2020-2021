package io.dama.ffi.actor;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import io.dama.ffi.messages.FindMsg;
import io.dama.ffi.messages.PleaseCleanupAndStop;
import io.dama.ffi.messages.ResultMsg;

public class MasterActor extends AbstractActor {
    private int numOfOpenSubTasks;
    private final List<String> result = new ArrayList<>();
    private /* final */ ActorRef listener;

    public MasterActor() {
        // TODO
        // this.listener = ein neuer ListenerActor
        // optional: this.listener überwachen
        // final bei den entsprechenden Instanzvariablen setzen
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(FindMsg.class, this::handleFindMsg) //
                .match(ResultMsg.class, this::handleResultMsg) //
                .build();
    }

    private void handleFindMsg(final FindMsg msg) {
        // TODO
        // über alle fileNames der FindMsg iterieren:
        // - neue WorkMsg erzeugen
        // - neuen WorkerActor erzeugen
        // - optional: WorkerActor Überwachung starten
        // - WorkMsg mit tell an WorkerActor schicken
        // - numOfOpenSubTasks++
    }

    private void handleResultMsg(final ResultMsg msg) {
        getSender().tell(new PleaseCleanupAndStop(), getSelf());
        this.numOfOpenSubTasks--;
        this.result.addAll(msg.getResult());
        if (this.numOfOpenSubTasks == 0) {
            this.listener.tell(new ResultMsg(this.result), getSelf());
            getContext().stop(getSelf());
        }

    }

}
