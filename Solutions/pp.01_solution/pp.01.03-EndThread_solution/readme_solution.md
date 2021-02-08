# Das Thread-Konzept von Java #

## Lebenszyklus von Threads und _Interrupts_ ##

Das Beenden von Threads ist heikel und wird oft falsch gemacht. Im folgenden Beispiel erhalten Sie ein Muster für korrektes Beenden, das Sie jederzeit in eigenen Beispielen reproduzieren können sollten.

Die Grundidee ist, eine ``boolean``-Instanzvariable zu verwenden, die als **Ende-Signal-Flag** benutzt wird um zu markieren, dass der Thread terminieren soll. Das Flag wird dann im Programmfluss des Threads (im Beispiel hier ist es eine ``while``-Schleife) kontrolliert und ggf. zu einem Aufräumteil verzweigt, nach dem der Thread (btw. die Methode ``run`` des Threads) terminiert. 

Außerdem geht es in diesem Beispiel darum wie mit etwaigen nicht behandelten **Runtime-Exception** umgegangen werden kann. Dazu wird im Runner eine "Division durch 0"-Exception provoziert, die durch eine Routine abgefangen wird, die "von außen" am Thread angebracht wird, der das ``Runnable`` ``Task`` ablaufen lassen wird.

## Aufgaben ##

* Analysieren Sie ``Task``. 
* Wie lange wird mit ``Thread.sleep()`` gewartet, bis es zur ``Exception`` kommt?

  - 1000ms/10 + 1000ms/9 + 1000ms/8 + ... 1000ms/1 
  - (100+111+125+143+167+200+250+333+500+1000)ms 
  - 2929ms 

* Lassen Sie ``Runner`` laufen.
* Analysieren Sie ``Runner``.
* Es sollte eine Exception auftreten. Wo wird sie behandelt?

  - in ``uncaughtException(...)`` der anonymen inneren Klasse von ``UncaughtExceptionHandler`` in ``Runner``

* Ändern Sie ``Runner`` so, dass nach dem Start von ``task`` ein weiterer Thread erzeugt und gestartet wird, der die ``Task``-Instanz ``task`` von außen beendet. 

  **Tipp:** Sparen Sie unnötige Tipparbeit und verwenden Sie einen Lambda-Ausdruck, um das Functional Interface (``run()``) von ``Runnable`` zu füllen, den Sie einfach einer neuen ``Thread``-Instanz übergeben können, die Sie sogleich starten (ein "Einzeiler").
  
		(new Thread(() -> thread.interrupt())).start(); // falsch: thread läuft
														// weiter
		(new Thread(() -> task.stopRequest())).start(); // richtig: thread wird
														// beendet
   

* Es sollte eine Exception auftreten. Wo wird sie behandelt?

  - im ``catch``-Block in der ``run()``-Methode in ``Task``:
  
		try {
			Thread.sleep(1000 / i--);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		} 
		
   weil ``this.self.interrupt())`` in ``stopRequest()` aufgerufen wird, während ``Thread.sleep(1000 / i--)`` ausgeführt wird. 
    
* Warum ist es erforderlich in ``stopRequest()`` von ``Task`` dem Thread von ``Task``, dessen Referenz in ``self`` gespeichert ist, mit ``interrupt()`` ein Signal zu schicken, ob wohl in die Hauptschleife von ``run()`` doch dadurch abgebrochen wird?

  - sonst wird ein lange laufendes ``Thread.sleep(...)`` nicht unterbrochen, sondern zu Ende geführt.   
