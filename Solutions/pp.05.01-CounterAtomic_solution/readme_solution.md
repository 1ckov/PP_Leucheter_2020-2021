# Atomic Variablen #

## Counter threadsicher machen ##

Die Klasse ``Counter`` ist nicht threadsicher.

## Aufgaben ##


* Schreiben Sie jeweils einen Testfall um ``increment`` und ``decrement`` zu testen.
Mit den Testfällen soll demonstriert werden, dass ``Counter`` nicht threadsicher ist. Das kann bspw. dadurch erreicht werden, dass an demselben Objekt ``increment`` aus zwei unterschiedlichen Threads heraus aufgerufen wird. Formulieren Sie die Testfälle so, dass sie scheitern, wenn sich die Race-Condition jeweils auswirkt. Lassen Sie die Testfälle mehrfach laufen und überzeugen Sie sich davon, dass die Klasse tatsächlich nicht threadsicher ist.
* Machen Sie ``Counter`` durch ``synchronized`` threadsicher. 
* Lassen Sie die Testfälle erneut ablaufen und überzeugen Sie sich davon, dass sie nun erfolgreich laufen (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit).
* Machen Sie ``Counter`` mit einem ``AtomicInteger`` und ``compareAndSet`` threadsicher.
* Lassen Sie die Testfälle erneut ablaufen und überzeugen Sie sich davon, dass sie nun erfolgreich laufen (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit).

* Vergleichen Sie die Ausführungszeit der Testfälle.

* Machen Sie ``Counter`` mit einem ``AtomicInteger`` und ``in/decrementAndGet`` oder ``getAndDe/Increment`` threadsicher.
* Lassen Sie die Testfälle erneut ablaufen und überzeugen Sie sich davon, dass sie nun erfolgreich laufen (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit).


  * ``Counter``
  * zwei Threads greifen auf dasselber ``Counter``-Objekt über ``increment`` und ``decrement`` zu. 
  * Das Programm braucht auf einem MacBook Pro (Retina, 15', Mitte 2015) mit 2,5 GHz Intel Core i7 **3ms**.
  
* Machen Sie ``Counter`` durch ``synchronized`` threadsicher.
  
  * ``SynchronizedCounter``
  * durch ``synchronized`` für ``value``, ``increment`` und ``decrement``

* Machen Sie ``Counter`` mit einem ``AtomicInteger`` und ``compareAndSet`` threadsicher.

  * ``AtomicCounter1``
  * statt in einem Bereich mit gegenseitigem Ausschluss mit ``++`` und ``--`` zuzugreifen wird ``compareAndSet`` benutzt. 
  
* Erstellen Sie einen Testfall, mit dem gezeigt wird, dass ``Counter`` nun thread-sicher ist (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit). Messen Sie die Ausführungszeit.

  * ``AtomicCounter1``, Ausführungszeit **6 ms**

* Machen Sie ``Counter`` mit einem AtomicInteger und ``in/decrementAndGet`` oder ``getAndDe/Increment`` threadsicher.

  * ``AtomicInteger2``
  * statt in einem Berecih mit gegenseitigem Ausschluss mit ``++`` und ``--`` zuzugreifen wird ``incrementAndGet`` bzw. ``decrementAndGet``benutzt. 

* Erstellen Sie einen Testfall, mit dem gezeigt wird, dass ``Counter`` nun thread-sicher ist (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit). Messen Sie die Ausführungszeit.


  * ``AtomicCounter2``, Ausführungszeit ca. **4 ms**
  
  * ``AtomicCounter3``:
    Diese Lösung ist analog zu ``AtomicCounter1``, jedoch sind bei dieser Implementierung die Schleifen etwas anders gebaut, um quasi Quelltext-Gleichheit mit der internen Implementierung von ``incrementAndGet()``/``decrementAndGet()`` zu erreichen (s. [http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/concurrent/atomic/AtomicInteger.java#AtomicInteger.incrementAndGet%28%29](http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/concurrent/atomic/AtomicInteger.java#AtomicInteger.incrementAndGet%28%29))
  * Ausführungszeit ca. **4 ms**