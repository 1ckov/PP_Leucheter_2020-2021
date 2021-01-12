package io.dama.ffi.actors.find;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import io.dama.ffi.actors.find.messages.FindMsg;
import io.dama.ffi.actors.find.messages.PleaseCleanupAndStop;
import io.dama.ffi.actors.find.messages.ResultMsg;
import io.dama.ffi.actors.find.messages.WorkMsg;

public class MasterActor extends AbstractActor {
    private int numOfChild;
    private final List<String> result = new ArrayList<>();
    private final ActorRef listener;

    public MasterActor() {
        this.listener = getContext().actorOf(Props.create(ListenerActor.class), "listener");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(FindMsg.class, this::handleFindMsg) //
                .match(ResultMsg.class, this::handleResultMsg) //
                .build();
    }

    private void handleFindMsg(final FindMsg msg) {
        var filenames = msg.getFilenames();
        var searchword = msg.getSearchword();
        this.numOfChild = msg.getFilenames().size();
        for (var filename : filenames) {
            var job = new WorkMsg(filename, searchword);
            var findActor = getContext().actorOf(Props.create(WorkerActor.class));
            findActor.tell(job, getSelf());
        }

    }

    private void handleResultMsg(final ResultMsg msg) {
        getSender().tell(new PleaseCleanupAndStop(), getSelf());
        this.numOfChild--;
        this.result.addAll(msg.getResult());
        if (this.numOfChild == 0) {
            this.listener.tell(new ResultMsg(this.result), getSelf());
            getContext().stop(getSelf());
        }

    }

}
