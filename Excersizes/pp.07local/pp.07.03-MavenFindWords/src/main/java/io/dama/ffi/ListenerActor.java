package io.dama.ffi;

import akka.actor.AbstractActor;

public class ListenerActor {
    // TODO diese Klasse muss von AbstractActor oder AbstractLoggingActor erben

    // TODO Methode createReceive implementieren:
    // Wegen der Oberklasse muss die dort als abstract gekennzeichnete Methode
    // createReceive überschrieben werden (verwenden Sie einfach die eclipse-Funktion 
    // "add unimplemented methods", nachdem Sie die Oberklasse von ListenerActor
    // mit extends spezifiziert haben.

    // der "Receiver" muss mit "match" eine Funktion für ResultMsg.class-Objekte
    // deklarieren. Sie soll die ResultMsg-Objekte (bzw. deren result-List<String>
    // [kann mit .getResult() abgerufen werden]) ausgeben.
}
