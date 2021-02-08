package io.dama.ffi.actors.find;

import akka.actor.AbstractActor;
import io.dama.ffi.actors.find.messages.ResultMsg;

public class ListenerActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(ResultMsg.class, (msg) -> {
                    msg.getResult().forEach(System.out::println);
                    getContext().getSystem().terminate();
                }).build();
    }

}
