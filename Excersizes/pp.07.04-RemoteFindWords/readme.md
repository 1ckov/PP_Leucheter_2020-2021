# Actors #

## Akka-Actors mit Java: verteilte Actors und Router/Routees ##

Damit kein großer Aufwand bei der Verteilung der Akka-Systeme in einem Netzwerk erforderlich ist, werden hier zwei getrennte Akka-Prozesse auf demselben Rechner gestartet. Sie kommen aber aus demselben Eclipse-Projekt, in dem zwei unterschiedliche Main-Klassen (Klassen mit der Methode ``public static void main(final String... args)``) enthalten sind, die nacheinander gestartet werden müssen.

### Aufgaben ###

* Bereiten Sie das Maven-Projekt auf die Verwendung mit Akka-Remote vor (Java 8 ist erforderlich).
* Kopieren Sie die Inhalte aus dem Projekt ``pp.07.02-MavenFindWords`` bzw. ``pp.07.02-MavenFindWords_solution`` als "Starthilfe" in das neue Projekt.
* Modifizieren Sie die Hauptklasse so, dass eine Konfigurationsdatei eingelsen und verwendet wird.
* Spalten Sie die Hauptklasse in mindestens zwei Hauptklassen, die zwei getrennte Akka-Systeme (z.B. auf demselben lokalen Rechenr) starten.
* Für verteiltes Akka müssen Nachrichten ``Serializable`` sein. Ändern Sie die Klassen entsprechend.
* Erzeugen Sie entsprechende Konfigurationen, die von den Hauptklassen benutzt werden sollen, so dass die Akka-Systeme miteinander kommunizieren und so dass die Actors auf einem anderen Akka-System laufen als der Rest. 

**Tipp:**

* ``ListenerActor``, ``WorkerActor`` und ``MasterActor`` (aus ``io.dama.ffi.router``) bleiben unverändert.
* ``io.dama.ffi.messages.*`` und die Hauptklasse (zum Starten) müssen an den verteilten Betrieb angepasst werden.
