package io.dama.ffi.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.List;
import java.util.ArrayList;

public class ListenerActor extends AbstractActor {
    Router router;

    public ListenerActor(final String message) {
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(Worker.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        this.router = new Router(new RoundRobinRoutingLogic(), routees);

        // ...
        router.route(message, getSender());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()//
                .match(Object.class, (o) -> System.out.println(o))//
                .build();
    }
}
