# Das Thread-Konzept von Java #

## Lebenszyklus von Threads und _Interrupts_ ##

Das Beenden von Threads ist heikel und wird oft falsch gemacht. Im folgenden Beispiel erhalten Sie ein Muster für korrektes Beenden, das Sie jederzeit in eigenen Beispielen reproduzieren können sollten.

Die Grundidee ist, eine ``boolean``-Instanzvariable zu verwenden, die als **Ende-Signal-Flag** benutzt wird um zu markieren, dass der Thread terminieren soll. Das Flag wird dann im Programmfluss des Threads (im Beispiel hier ist es eine ``while``-Schleife) kontrolliert und ggf. zu einem Aufräumteil verzweigt, nach dem der Thread (btw. die Methode ``run`` des Threads) terminiert. 

Außerdem geht es in diesem Beispiel darum wie mit etwaigen nicht behandelten **Runtime-Exception** umgegangen werden kann. Dazu wird im Runner eine Division durch 0-Exception provoziert, die durch eine Routine abgefangen wird, die "von außen" am Thread angebracht wird, der das ``Runnable`` ``Task`` ablaufen lassen wird.

## Aufgaben ##

* Analysieren Sie ``Task``. 
* Wie lange wird mit Thread.sleep gewartet, bis es zur Exception kommt?
* Lassen Sie ``Runner`` laufen.
* Analysieren Sie ``Runner``.
* Es sollte eine Exception auftreten. Wo wird sie behandelt?
* Ändern Sie ``Runner`` so, dass nach dem Start von ``task`` ein weiterer Thread erzeugt und gestartet wird, der die ``Task``-Instanz ``task`` von außen beendet. 

  **Tipp:** Sparen Sie unnötige Tipparbeit und verwenden Sie einen Lambda-Ausdruck, um das Functional Interface (``run()``) von ``Runnable`` zu füllen, den Sie einfach einer neuen ``Thread``-Instanz übergeben können, die Sie sogleich starten (ein "Einzeiler"). 

* Es sollte eine Exception auftreten. Wo wird sie behandelt?
* Warum ist es erforderlich in ``stopRequest()`` von ``Task`` dem Thread von ``Task``, dessen Referenz in ``self`` gespeichert ist, mit ``interrupt()`` ein Signal zu schicken, ob wohl in die Hauptschleife von ``run()`` doch dadurch abgebrochen wird?   
