# Actors #

## Akka: Paralleles Suchen/Finden eines Suchstrings in Textdateien ##

## Aufgaben ##
* Erstellen Sie die neue Klasse ``ListenerActor`` in Package ``io.dama.ffi.actors.find``. Sie soll von der Klasse ``akka.actor.AbstractActor`` erben und ``ResultMsg``-Objekte empfangen können. Beim Empfang solch einer Nachricht sollen alle Elemente des Properties ``Result`` ausgegeben werden. Danach soll das gesamte System mit ``getContext().getSystem().terminate();`` heruntergefahren werden.
* Ein ``ListenerActor`` soll im Konstruktor von ``MasterActor`` erzeugt werden und in der Instanzvariablen ``listener`` gespeichert werden. 
* Der ``MasterActor`` soll beim Empfang einer ``FindMsg`` mit ``getFilenames()`` herausfinden, wieviele Dateien durchsucht werden sollen. Für jede Datei soll ein eigener ``WorkerActor`` erzeugt werden, dem sofort mit ``tell`` eine neue ``WorkMsg`` mit dem entsprechenden Auftrag geschickt wird.
* Der ``WorkerActor`` soll eine Nachricht vom Typ ``ResultMsg`` an den ``MasterActor`` zurücksenden, wenn er fertig ist. In der ``ResultMsg`` soll das Ergebnis (lokale Variable ``result``) enthalten sein.
 
