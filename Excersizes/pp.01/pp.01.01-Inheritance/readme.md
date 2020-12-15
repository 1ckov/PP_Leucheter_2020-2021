# Das Thread-Konzept von Java #

## Start eines ``Thread`` durch und bei Vererbung ##

Im Package ``io.dama.ffi.threads.inheritence`` ist die Klasse ``Thermometer``, die von ``Sensor`` erbt, welche wiederum von ``Thread`` erbt. ``Thermomenter`` ist also auch ein ``Thread`` und kann nebenläufig durch Aufruf von ``start()`` ablaufen.

Jeder ``Sensor`` ist nebenläufig. Er hat als generische Funktionalität regelmäßig in einem durch das Property ``frequency`` festgelegten Intervall einen Messwert auszulesen. Der Messwert kommt aus der Methode ``reading()``. Inhaltlich ist ``Sensor`` _abstract_. Für Testzwecke können jedoch auch von ``Sensor`` Instanzen gebildet werden. Sensor hat eine ``main``-Methode. Hier wird eine Instanz gebildet und sie läuft nebenläufig los, da das so im Konstruktor bestimmt wird.

Sub-Klassen von ``Sensor``, wie ``Thermometer`` überschreiben die ``reading()``-Methode. In ``Thermometer`` sollen in der ``main``-Methode zwei Instanzen gebildet werden, die mit unterscheidlicher ``frequency`` Messungen vornehmen sollen.

## Aufgaben ##

*  Der vorliegende Quellcode ist nur teilweise funktionsfähig. Probieren Sie die ``main``-Methoden von ``Sensor`` und ``Thermometer`` aus. 
* Analysieren Sie den Programmablauf.
* Korrigieren Sie das Programm, so dass beide Klassen lauffähig sind.
* Verallgemeinern Sie Ihre **Findings** zu einem kurzen Merksatz!

 