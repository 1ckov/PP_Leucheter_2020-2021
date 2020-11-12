# Thread Steuerung #

## wait/notifyAll für Ringpuffer ##


## Aufgaben ##

* Analysieren Sie den Quellcode. 
* Lassen Sie das Hauptprogramm mehrfach laufen und beobachten Sie das Verhalten. Entspricht dies der Spezifikation?
* Modifizieren Sie die Methode ``put`` so, dass gewartet wird, falls der Puffer voll ist (``items.count==lentgh``). Dazu muss ``take`` alle wartenden Threads aufwecken, wenn ein Element entfernt wurde, weil im Puffer danach wieder etwas Platz ist.
* Modifizieren Sie die Methode ``take`` so, dass gewartet wird, falls der Puffer leer ist (``items.count==0``).  Dazu muss ``put`` alle wartenden Threads aufwecken, wenn ein Element hinzugefügt wurde, weil im Puffer danach wieder etwas zu holen ist.