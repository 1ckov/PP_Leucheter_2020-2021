package io.dama.ffi.actors;

import akka.actor.AbstractActor;

public class MasterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()//
                .match(Object.class, (o) -> System.out.println(o))//
                .build();
    }
}
