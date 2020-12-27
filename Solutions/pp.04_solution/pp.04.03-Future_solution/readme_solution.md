# Parallele Programmierung. Thread Pools #

## Thread für Runnable mit Rückgabewert wie in pp04.01, nun aber mit ``Future``'s ##

## Aufgaben ##


### ``Callable<T>`` und ``Future<T>`` ###

Für ``Expression<T>`` und ``RunnableWithResult<T>`` gibt es seit Java 5 ``Callable<T>`` und ``Future<T>``. Bilden Sie zwei Paare: Welches Java 5 Interface ersetzt das selbst entwickelte ``Expression<T>`` und welches ``RunnableWithResult<T>``?  


### ``Main`` ###

Implementieren Sie dieselbe Funktionalität wie in pp04.01 mit ``Future`` und ``Callable``: *Erzeugen Sie drei ``RunnableWithResult``-Objekte für die Berechnung des Ausdrucks ((1+2)+(3+4)), die nebenläufig zum Main-Thread ausgeführt werden. Das Ergebnis soll ausgegeben werden. Sie können *Lambda-Ausdrücke* oder *Inner Classes* benutzen um die ``Expression``'s zu erzeugen.*

### Exceptions ###
 
Was sind die Unterschiede zwischen den selbst entwickelten ``Expression<T>``/``RunnableWithResult<T>`` und ``Callable<T>``/``Future<T>`` beim Exception-Handling? Provozieren Sie wieder eine Exception durch Division durch 0. Wann wird sie nun realisert und wo muss man sie behandeln?

## Lösungsskizze ##

### ``Callable<T>`` und ``Future<T>`` ###

* ``Expression<T>`` analog zu  ``Callable<T>`` (``call()`` statt ``eval()``)
* ``RunnableWithResult<T>`` analog zu ``Future<T>`` (``get()`` und ``isAvailable()`` wie ``!isDone()``)


### ``Main`` ###

* s. ``Main.main(args)`` (einmal als anonymous inner Class, zweimal als Lambda-Ausdruck)
* ``get()`` blockiert, bis ein Ergebnis vorliegt. Die Warteschleifen werden deshalb nicht benötigt.

### Exceptions ###

Die Exception wird erst beim Zugriff mit ``get()`` realisert. Eine Exeption in einem ``Callable`` eines ``Future``-Threads wird erst beim Zugriff mit ``get()`` an den Aufrufer weitergereicht. Dort muss sie behandelt werden, nicht in der ``call()``-Methode des ``Callable``'s.