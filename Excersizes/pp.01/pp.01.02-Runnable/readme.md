# Das Thread-Konzept von Java #

## Start eines ``Thread`` als ``Runnable`` und Refactoring ##


## Aufgaben ##
* Lassen Sie ``Starter`` laufen.
* Ändern Sie den ``Starter`` so, dass statt ``MyWorker`` Instanzen von ``MyWorkerCoop`` erzeugt werden.
* Vergleichen Sie das Verhalten von beiden Varianten. Recherchieren Sie die API-Beschreibung von ``Thread.yield()`` und erklären Sie sich den Unterschied.
* Erzeugen Sie eine Kopie der Klasse ``Starter`` namens ``StarterInner``, die dieselbe Funktion hat wie ``Starter`` aber anstatt einer externen Klasse für den _Worker_ eine **anonyme innere Klasse** verwendet.  
* Erzeugen Sie eine Kopie der Klasse ``Starter`` namens ``StarterLambda``, die dieselbe Funktion hat wie ``Starter`` aber anstatt einer externen Klasse für den _Worker_ einen **Lambda-Ausdruck** verwendet.  

 