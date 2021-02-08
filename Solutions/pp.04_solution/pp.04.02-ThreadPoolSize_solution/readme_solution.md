# Parallele Programmierung. Thread Pools #

## Thread Pools ##

Bei diesem Projekt ist das JAR in lib in den CLASSPATH eingebunden (falls nicht: Rechtsklick auf das JAR in lib und "Add to Classpath" wählen). In dem JAR sind zwei Klassen (``io.dama.ffi.pool.run.fixed.Runner`` und ``io.dama.ffi.pool.run.cached.Runner``), die beide jeweils die statische Methode ``test(Runnable r, int tries)`` bereitstellen. In diesen beiden ``Runner.test(...)``-Methoden wird ein Thread Pool angelegt. Das ``Runnable`` ``r`` wird dann ``tries`` mal im Thread Pool ausgeführt.

In ``io.dama.ffi.pool.run.fixed.Runner`` wird dafür ein Thread Pool fester Größe benutzt und in ``pp.pool.run.cached.Runner` ein Cached Thread Pool.

## Aufgaben ##

* Inspizieren Sie den Thread Pool von ``Runner.test()`` indem Sie die ``run()``-Methode mit eigenem Code füllen. Versuchen Sie durch Testläufe zu ergründen, wie groß der Thread Pool jeweils ist bzw. wie er sich verhält. Verwenden Sie dabei statische, anonyme innere Klassen oder Lambda-Ausdrücke.
* Fahren Sie am Ende den Threadpool nach einer kurzen Wartezeit durch ein Scheduled Event herunter.

## Lösungsskizze ##

Die Größe des Fixed Thread Pools ist 6. Die maximale Größe des Cached Thread Pools hängt von Ihrem Rechner und vom Timing der Tasks ab. 

Die folgenden Klassen zeigen Lösungsskizzen mit unterschiedlichen Methoden:
* ``TaskInnerClass``: anonyme innere Klassen
* ``TaskLambda``: Lambda-Ausdrücke
* ``TaskStaticClass``: statische Klassen

Am Ende wird ein ``ScheduledExecutorService`` namens ``scheduler`` benutzt um nach einer Verzögerung von 5 Sekunden beide Thread Pools mit ``shutdown()`` herunterzufahren. Ohne dies bleiben die Thread Pools aktiv. 