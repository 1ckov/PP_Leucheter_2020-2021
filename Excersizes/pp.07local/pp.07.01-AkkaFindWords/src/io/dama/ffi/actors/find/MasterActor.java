package io.dama.ffi.actors.find;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import io.dama.ffi.actors.find.messages.FindMsg;
import io.dama.ffi.actors.find.messages.PleaseCleanupAndStop;
import io.dama.ffi.actors.find.messages.ResultMsg;

public class MasterActor extends AbstractActor {
    private int numOfChild;
    private final List<String> result = new ArrayList<>();
    private ActorRef listener;

    public MasterActor() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(FindMsg.class, this::handleFindMsg) //
                .match(ResultMsg.class, this::handleResultMsg) //
                .build();
    }

    private void handleFindMsg(final FindMsg msg) {
    	// TODO ausformulieren
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
