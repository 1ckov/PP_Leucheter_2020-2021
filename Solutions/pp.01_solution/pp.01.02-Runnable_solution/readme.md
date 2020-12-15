# Das Thread-Konzept von Java #

## Start eines ``Thread`` als ``Runnable`` und Refactoring ##


## Aufgaben ##
* Lassen Sie ``Starter`` laufen.
* Ändern Sie den ``Starter`` so, dass statt ``MyWorker`` Instanzen von ``MyWorkerCoop`` erzeugt werden.
* Vergleichen Sie das Verhalten von beiden Varianten. Recherchieren Sie die API-Beschreibung von ``Thread.yield()`` und erklären Sie sich den Unterschied.

 - Mit ``yield()`` wird dem Scheduler signalisiert, dass beim Pseudo-Multitasking ein Wechsel zum nächsten wartenden Thread möglich ist. Der ``yield()`` aufrufende Thread gibt die Ressourcen zu diesem Punkt aktiv an den Scheduler zurück. Allerdings müssen JVM-Scheduler-Implementierungen dies nicht berücksichtigen. Falls doch, wird es dadurch öfter zu Kontextwechseln kommen.
 -  s. [Java-API](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#yield())

* Erzeugen Sie eine Kopie der Klasse ``Starter`` namens ``StarterInner``, die dieselbe Funktion hat wie ``Starter`` aber anstatt einer externen Klasse für den _Worker_ eine **anonyme innere Klasse** verwendet.  
* Erzeugen Sie eine Kopie der Klasse ``Starter`` namens ``StarterLambda``, die dieselbe Funktion hat wie ``Starter`` aber anstatt einer externen Klasse für den _Worker_ einen **Lambda-Ausdruck** verwendet.  

 - Die Instanzvariable ``self`` muss zu einer lokalen Variable in ``run()`` gemacht werden. Inhaltlich ist das in diesem Fall kein Problem.

 