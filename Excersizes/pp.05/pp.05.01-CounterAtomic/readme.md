# Atomic Variablen #

## Counter threadsicher machen ##

Die Klasse ``Counter`` ist nicht threadsicher.

**Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit**

## Aufgaben ##

* Schreiben Sie jeweils einen Testfall um ``increment`` und ``decrement`` zu testen.
Mit den Testfällen soll demonstriert werden, dass ``Counter`` nicht threadsicher ist. Das kann bspw. dadurch erreicht werden, dass an demselben Objekt ``increment`` aus zwei unterschiedlichen Threads heraus aufgerufen wird. Formulieren Sie die Testfälle so, dass sie scheitern, wenn sich die Race-Condition jeweils auswirkt. Lassen Sie die Testfälle mehrfach laufen und überzeugen Sie sich davon, dass die Klasse tatsächlich nicht threadsicher ist.
* Machen Sie ``Counter`` durch ``synchronized`` threadsicher. 
* Lassen Sie die Testfälle erneut ablaufen und überzeugen Sie sich davon, dass sie nun erfolgreich laufen (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit).
* Machen Sie ``Counter`` mit einem ``AtomicInteger`` und ``compareAndSet`` threadsicher.
* Lassen Sie die Testfälle erneut ablaufen und überzeugen Sie sich davon, dass sie nun erfolgreich laufen (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit).

* Vergleichen Sie die Ausführungszeit der Testfälle.

* Machen Sie ``Counter`` mit einem AtomicInteger und ``in/decrementAndGet`` oder ``getAndDe/Increment`` threadsicher.
* Lassen Sie die Testfälle erneut ablaufen und überzeugen Sie sich davon, dass sie nun erfolgreich laufen (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit).

