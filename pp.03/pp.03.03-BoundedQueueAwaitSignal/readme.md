# Thread Steuerung #

## await/signalAll für Ringpuffer ##

Im Projekt ``pp03.02-BoundedQueueWaitNotify`` wurde eine Bedingungsvariable für zwei unterschiedliche Situationen benutzt: 

* Puffer ist leer => alle Threads, die ``take()`` machen wollen, müssen warten 
* Puffer ist voll => alle Threads, die ``put()`` machen wollen, müssen warten 

Diese beiden Bedingungen sind aber inhaltlich unabhängig voneinander. Vom gegenseitigen Ausschluss wegen des konkurrierenden Zugriffs auf dieselbe Variable (``mem``) abgesehen, brauchen Threads, die ``take()`` machen wollen, nicht warten, wenn der Puffer voll ist und umgekehrt.

Deshalb sollen nun mit dem ``Lock``-Interface zwei getrennte Bedingungsvariablen (``Condition``) erzeugt werden, die getrennt zum Signalisieren der beiden Situationen benutzt werden sollen. 

## Aufgaben ##

* Modifizieren Sie die Methoden ``put`` und ``take`` so, dass zwei getrennte Bedingungsvariablen verwendet werden.