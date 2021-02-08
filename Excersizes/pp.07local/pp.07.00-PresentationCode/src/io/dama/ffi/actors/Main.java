package io.dama.ffi.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main extends AbstractActor {
    final ActorSystem system = ActorSystem.create();
    ActorRef myActor = this.system.actorOf(Props.create(MasterActor.class), "master");
    ActorRef listener = getContext().actorOf(Props.create(ListenerActor.class), "listener");

    @Override
    public Receive createReceive() {
        return null;
    }

}
