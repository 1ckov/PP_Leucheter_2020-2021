package io.dama.ffi.actors.find;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.dama.ffi.actors.find.messages.FindMsg;

public class Main {

    public static void main(final String... args) {
        var system = ActorSystem.create();
        var master = system.actorOf(Props.create(MasterActor.class), "master");
        String[] files = { "test1.txt", "test2.txt", "test3.txt", "test4.txt" };
        var msg = new FindMsg(files, "Erlang");
        master.tell(msg, ActorRef.noSender());
    }
}
