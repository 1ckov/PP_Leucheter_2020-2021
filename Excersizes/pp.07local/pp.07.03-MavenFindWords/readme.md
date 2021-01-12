# Actors #

## Akka-Actors mit Java: lokale Actors und Router/Routees ##

Im Projekt gibt es zwei vorbereitete Zweige in den Paketen

	io.dama.ffi.actor
	io.dama.ffi.router
	
Beide Zweige haben einige Klassen gemeinsam, die sich im Paket

	io.dama.ffi
	
befinden.


### ``io.dama.ffi.actor`` (ohne Router) ###

* ``Main`` erzeugt einen ``MasterActor`` und schickt ihm eine ``FindMsg``.
* Beim Erzeugen eines ``MasterActor`` wird ein ``ListenerActor`` als Kind-Actor erzeugt (zweckmäßigerweise im Konstruktor von ``MasterActor``).
* Der ``ListenerActor`` soll am Ende die Fundstellen entgegennehmen und ausgeben.
* Wenn der ``MasterActor`` die ``FindMsg`` erhält, soll er die Teilaufgaben entnehmen und für jede Teilaufgabe einen ``WorkerActor`` erzeugen, dem die Teilaufgabe als ``WorkMsg`` gesendet wird.
* Jeder ``WorkerActor`` löst seine Teilaufgabe (prüfen, ob der Suchstring in der ihm übergebenen Datei zu finden ist) und erzeugt eine ``ResultMsg`` in der ggf. die Fundstellen enthalten sind. Die ``ResultMsg`` geht an den ``MasterActor``.
* Der ``MasterActor`` sammelt alle ``ResultMsg``-Objekte, bis die Anzahl der ``WorkMsg``-Instanzen erreicht ist, die dafür in der Instanzvariable ``numOfOpenSubTasks`` gespeichert werden muss. Das ist das Zeichen, dass das Gesamtproblem gelöst ist. Der ``MasterActor`` fusioniert alle ``ResultMsg`` zu einer einzigen ``ResultMsg``, die an den ``ListenerActor`` gesendet wird.
* Wenn der ``MasterActor`` eine ``ResultMsg`` von einem "seiner" ``WorkerActor`` bekommt, wird dieser ``WorkerActor`` nicht mehr gebraucht. Deshalb schickt der ``MasterActor`` beim Empfang einer ``ResultMsg`` eine ``PoisonPill``-Nachricht an den Absender (ein ``WorkerActor``), der darufhin stoppt.   



### ``io.dama.ffi.router`` (mit Router) ###

* ``Main`` erzeugt einen ``MasterActor`` und schickt ihm eine ``FindMsg``.
* Beim Erzeugen eines ``MasterActor`` wird ein ``ListenerActor`` als Kind-Actor erzeugt (zweckmäßigerweise im Konstruktor von ``MasterActor``).
* Der ``ListenerActor`` soll am Ende die Fundstellen entgegennehmen und ausgeben.
* Beim Erzeugen des ``MasterActor`` müssen zusätzlich zum ``ListenerActor`` noch ``WORKER_NUM`` ``WorkerActor`` erzeugt werden. Die ``WorkerActor``-Instanzen werden mit dem Konstruktor ``ActorRefRoutee``, dem die jeweilige ``ActorRef`` als Parameter übergeben wird, zur *Routees*, die im Container ``routees`` gespeichert werden. Diese Liste wird dann beim Erzeugen eines neuen ``Router`` zusammen mit der *RoutingLogic* übergeben.  
* Wenn der ``MasterActor`` die ``FindMsg`` erhält, soll er die Teilaufgaben entnehmen und die Teilaufgaben als ``WorkMsg`` über den ``Router`` an die Worker verteilen.
* Jeder ``WorkerActor`` löst seine Teilaufgabe (prüfen, ob der Suchstring in der ihm übergebenen Datei zu finden ist) und erzeugt eine ``ResultMsg`` in der ggf. die Fundstellen enthalten sind. Die ``ResultMsg`` geht an den ``MasterActor``.
* Der ``MasterActor`` sammelt alle ``ResultMsg``-Objekte, bis die Anzahl der Teilaufgaben erreicht ist, die dafür in der Instanzvariable ``numOfOpenSubTasks`` gespeichert werden muss. Das ist das Zeichen, dass das Gesamtproblem gelöst ist. Der ``MasterActor`` fusioniert alle ``ResultMsg`` zu einer einzigen ``ResultMsg``, die an den ``ListenerActor`` gesendet wird.
* Wenn der ``MasterActor`` die letzte ``WorkMsg`` bekommt, ist das das Zeichen, dass die ``WorkerActor`` nicht mehr gebraucht werden. Deshalb schickt der ``MasterActor`` beim Empfang der letzten ``ResultMsg`` eine ``PoisonPill``-Nachricht direkt (also nicht über den ``Router``) an alle ``WorkerActor``, die darufhin stoppen.   


## Aufgaben ##

* Die Klassen ``io.dama.ffi.ListenerActor`` sowie ``io.dama.ffi.actor.MasterActor``/``io.dama.ffi.router.MasterActor`` müssen überarbeitet werden.
* Analysieren Sie die Suchaufgaben und entscheiden Sie welche *RoutingLogic* am geeignetsten ist, um die acht Teilaufgaben auf die fünf Worker zu verteilen.

*  Achtung: das *Working Directory* beim Ausführen von Maven-Projekten mit

	mvn exec:java -Dexec.mainClass=io.dama.ffi.actor.router.Main

ist das Wurzelverzeichnis des Projekts. Deshalb werden die Dateien in ``src/main/resources`` nicht gefunden. 

Werden die Programme in eclipse mit *Run* gestartet, muss die *Run Configuration* angepasst werden: *Run*->*Run Configurations...* Unter dem Reiter *Arguments* im Abschnitt *Working Directory* / *Other* 

	${workspace_loc:pp.07.02-MavenFindWords_solution/target/classes}
	
   oder entsprechend.  