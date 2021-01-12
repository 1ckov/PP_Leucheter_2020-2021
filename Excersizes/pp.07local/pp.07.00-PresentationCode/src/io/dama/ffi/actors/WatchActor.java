package io.dama.ffi.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;

public class WatchActor extends AbstractActor {
    ActorRef child = getContext().actorOf(Props.create(ListenerActor.class), "listener");
    ActorRef lastSender;
    public WatchActor() {
        getContext().watch(this.child);
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder() 
            .match(PoisonPill.class, (msg) -> {
                getContext().stop(this.child);
                this.lastSender = getSender(); })
            .match(Terminated.class, (msg) -> {
                if (msg.getActor() == this.child) {
                    this.lastSender.tell("finished", null);
                } })
            .build();
    }
}
