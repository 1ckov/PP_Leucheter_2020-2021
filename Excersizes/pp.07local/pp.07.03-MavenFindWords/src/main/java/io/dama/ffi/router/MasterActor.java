package io.dama.ffi.router;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.routing.Routee;
import akka.routing.Router;
import io.dama.ffi.messages.FindMsg;
import io.dama.ffi.messages.PleaseCleanupAndStop;
import io.dama.ffi.messages.ResultMsg;

public class MasterActor extends AbstractActor {
    private static final int WORKER_NUM = 5;
    private int numOfOpenSubTasks;
    private final List<String> result = new ArrayList<>();
    private /* final */ ActorRef listener;
    private /* final */ List<Routee> routees;
    private /* final */ Router router;

    public MasterActor() {
        // TODO
        // this.listener = ein neuer ListenerActor
        // optional: this.listener überwachen
        // this.routees mit leerer Liste initialisieren
        // Zählschleife von 0 bis MasterActor.WORKER_NUM-1
        // - neuen WorkerActor erzeugen
        // - optional: WorkerActor überwachen
        // - Routee für WorkerActor erzeugen (Konstruktor ActorRefRoutee)
        // - Routee der Liste this.routees hinzufügen
        // this.router = neuer Router mit passender Logik und this.routees als Routees
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
        // - WorkMsg mit route an Router schicken
        // - numOfOpenSubTasks++
    }

    private void handleResultMsg(final ResultMsg msg) {
        this.numOfOpenSubTasks--;
        this.result.addAll(msg.getResult());
        if (this.numOfOpenSubTasks == 0) {
            for (var routee : this.routees) {
                routee.send(new PleaseCleanupAndStop(), getSelf());
            }
            this.listener.tell(new ResultMsg(this.result), getSelf());
            getContext().stop(getSelf());
        }
    }
}
