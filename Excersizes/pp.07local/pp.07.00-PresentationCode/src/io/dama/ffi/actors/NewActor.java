package io.dama.ffi.actors;

import akka.actor.AbstractActor;

public class NewActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Msg1.class, this::receiveMsg1)
            .match(Msg2.class, this::receiveMsg2)
            .match(Msg3.class, this::receiveMsg3)
            .build();
        }
    private void receiveMsg1(final Msg1 msg) {/*...*/}
    private void receiveMsg2(final Msg2 msg) {/*...*/}
    private void receiveMsg3(final Msg3 msg) {/*...*/}
}

