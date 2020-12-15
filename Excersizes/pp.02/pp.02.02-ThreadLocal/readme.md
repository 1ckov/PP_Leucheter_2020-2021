# Konkurrierender Zugriff #

## Thread-lokaler Speicher ##

``Random`` hat eine Konstruktorvariante, die ein ``long`` als Parameter erwartet. Diese Zahl wird als _Seed_ benutzt, um den Zufallszahlengenerator mit einem Startwert zu konfigurieren. Alle Instanzen von ``Random``, die mit demselben Wert ``n`` als _Seed_ konfiguriert wurden, liefern dieselbe Sequenz von Zufallszahlen. Das ist beispielsweise beim Testen sehr hilfreich. 

In dieser Übungsaufgabe wird das benutzt, um unterschiedliche Instanzen von ``Random`` zu identifizieren und zu unterscheiden: In dem Programm in ``MyThreadLocalRandom`` werden 10 Threads gestartet. In jedem Thread werden jeweils 20 Zufallszahlen gezogen. Alle 20x10 Zufallszahlen werden vom selben Zufallszahlengenerator gezogen:

	public Random rand     

Sie kommen alle von derselben ``Random``-Instanz und es handelt sich deshalb um eine Sequenz von 200 unabhängigen Zufallszahlen.

``static`` _Members_ wie ``now`` werden initialisiert, wenn die umgebende Klasse in die JVM geladen wird. Instanzvariablen wie ``rand`` werden jeweils initialisiert, wenn eine Instanz mit dem Konstruktor erzeugt wird. 

## Aufgaben ##

* Ändern Sie ``MyThreadLocalRandom`` so, dass jeder Thread ein getrenntes Random-Objekt hat, das mit ``now`` als _Seed_ konfiguriert wurde, damit jeweils in den 10 Threads dieselbe Sequenz von 20 Zufallszahlen gezogen wird. 
