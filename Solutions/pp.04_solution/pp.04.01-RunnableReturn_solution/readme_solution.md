# Thread Pools #

## Thread für Runnable mit Rückgabewert ##

Es gibt viele Aufgaben, die man asynchron zum restlichen Programmfluss erledigen kann, die aber gleichzeitig ein konkretes Ergebnis zurückgeben sollen (z.B. Berechnungen, holen einer Information aus einem Backend über einen Netzwerkaufruf, ...).

Für diese Situation soll in dieser Aufgabe eine Wiederverwendbare Klasse programmiert werden. Das Interface ``Expression<T>`` repräsentiert eine Aufgabe mit Rückgabewert vom Typ ``T``.  ``Expression<T>`` ist ein ``@FunctionaInterface``: Es besitzt genau eine Methode, so dass es für Lambda-Ausdrücke benutzt werden kann. Die eigentliche Berechnung wird ausgeführt, wenn die zu implementierende Methode ``eval()`` ausgeführt wird. Der Rückgabewert von ``eval()`` ist das Ergebnis der Berechnung.

## Aufgaben ##

### ``RunnableWithResult<T>`` ###

In der Klasse ``RunnableWithResult<T>``, die das Interface ``Runnable`` implementiert, soll solch eine Aufgabe repräsentiert, ausgeführt und abgerufen werden können. Der Konstruktor erwartet ein Objekt, das ``Expression<T>`` implementiert. Es muss also die Methode ``eval()`` bereitstellen.

Die Methode ``run()`` muss überschrieben werden. In der Methode muss der Ausdruck ausgewertet werden. Das Ergebnis sollte in einer Instanzvariablen gespeichert werden. Um den Inhalt der Instanzvariablen aus einem aufrufenden Thread auslesen zu können, wird ein Getter benötigt. 

Sollte die Auswertung des Ausdrucks länger benötigen, muss der Aufrufer feststellen können, ob das Ergebnis schon vorliegt. Dafür ist die Methode ``isAvailable()`` vorgesehen. Getter und ``isAvailable()`` sollen beide asynchron arbeiten: Egal, ob ein Wert vorliegt oder nicht, der Aufruf blockiert nicht.

### ``Main`` ###
Erzeugen Sie drei ``RunnableWithResult``-Objekte für die Berechnung des Ausdrucks ((1+2)+(3+4)), die nebenläufig zum Main-Thread ausgeführt werden. Das Ergebnis soll ausgegeben werden. Sie können *Lambda-Ausdrücke* oder *anonyme innere Klassen* benutzen um die ``Expression``'s zu erzeugen.

### Exceptions ###
 
**Achtung:** Noch nicht so toll ist der Umgang mit *Exceptions* bei ``RunnableWithResult<T>``, die während der Auswertung des Ausdrucks auftreten könnten. Probieren Sie es aus, indem Sie einen "Division durch 0 Error" in einem ``RunnableWithResult<>``-Ausdruck provozieren. Wann wird die Exception realisiert?

## Lösungsskizze ##
### ``RunnableWithResult1<T>`` ###
* Result und Information über die Beendigung werden in privaten Instanzvariablen gespeichert (``result`` und ``finished``).
* Durch den ``synchronized``-Zugriff liegt jeder Zugriff auf eine Instanzvariable hinter einer Memory-Barrier.
* ``isAvailable()`` sollte nicht dadurch implementiert werden, dass geprüft wird, ob ``result`` noch ``null`` ist, denn das könnte schließlich auch das legitime Ergebnis eines Ausdrucks sein.  
* Den Zugriff auf ``get()`` könnte man leicht blockierend machen, wenn die Berechnung in ``run()`` auch in einem ``synchronized``-Block mit derselben Lock-Variable wie der Instrinsic Lock für ``get()`` ablaufen würde. 

### ``RunnableWithResult2<T>`` ###
* Result wird in privater Instanzvariablen gespeichert (``result``)
* Zum Beginn von run() wird der aktuelle Thread, also der Thread, in dem das ``RunnableWithResult2<T>`` ausgeführt wird, in der Instanzvariablen ``self`` gespeichert. 
* self ist volatile
* ``isAvailable()`` prüft, ob self *alive* ist. Ist der Thread ``self`` fertig, ist er nicht mehr alive. Daher muss dann das Ergebnis vorliegen. Allerdings kann es sein, dass ``run()`` noch gar nicht angefangen hat - in dem Fall ist ``self`` aber noch ``null``.  

### ``Main`` ###

s. ``Main``

### Exceptions ###

Eine Exception wird sofort geworfen, wenn sie passiert. Das ist ungünstig, weil die Fehersuche schwierig, wenn der Programmfluss dadurch asynchron unterbrochen wird. Außerdem ist ungünstig, dass die Behandlungsroutine sich nur in der ``Expression`` befinden kann. 