package io.dama.ffi.router;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.dama.ffi.messages.FindMsg;

public class Main {

    public static void main(final String... args) {
        var system = ActorSystem.create();
        var master = system.actorOf(Props.create(MasterActor.class), "master");
        String[] files = { "test1.txt", "test2.txt", "test3.txt", "test4.txt", "test5.txt", "test6.txt", "test7.txt",
                "test8.txt" };
        var msg = new FindMsg(files, "Erlang");
        master.tell(msg, ActorRef.noSender());
    }
}
