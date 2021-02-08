package io.dama.ffi.actors;

import akka.actor.AbstractLoggingActor;

public class DemoMessagesActor extends AbstractLoggingActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Greeting.class, message -> {
            log().info("I was greeted by {}", message.getGreeter());
        }).build();
    }
}
