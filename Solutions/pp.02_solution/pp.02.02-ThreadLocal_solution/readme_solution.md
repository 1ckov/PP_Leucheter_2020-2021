# Konkurrierender Zugriff #

## Thread-lokaler Speicher ##

``Random`` hat eine Konstruktorvariante, die ein ``long`` als Parameter erwartet. Diese Zahl wird als _Seed_ benutzt, um den Zufallszahlengenerator mit einem Startwert zu konfigurieren. Alle Instanzen von ``Random``, die mit demselben Wert ``n`` als _Seed_ konfiguriert wurden, liefern dieselbe Sequenz von Zufallszahlen. Das ist beispielsweise beim Testen sehr hilfreich. 

In dieser Übungsaufgabe wird das benutzt, um unterschiedliche Instanzen von ``Random`` zu identifizieren und zu unterscheiden: In dem Programm in ``MyThreadLocalRandom`` werden 10 Threads gestartet. In jedem Thread werden jeweils 20 Zufallszahlen gezogen. Alle 20x10 Zufallszahlen werden vom selben Zufallszahlengenerator gezogen:

	public Random rand     

Sie kommen alle von derselben ``Random``-Instanz und es handelt sich deshalb um eine Sequenz von 200 unabhängigen Zufallszahlen.

``static`` _Members_ wie ``now`` werden initialisiert, wenn die umgebende Klasse in die JVM geladen wird. Instanzvariablen wie ``rand`` werden jeweils initialisiert, wenn eine Instanz mit dem Konstruktor erzeugt wird. 

## Lösungshinweise ## 

Der Zufallsgenarator java.util.Random ist im Thread-Betrieb sehr ineffeizient, da weite Teile in gegenseitigem AUsschluss laufen, falls von mehreren Threads aus auf dieselbe Instanz von ``Random`` zugegriffen wird. Viel Effizienter ist es per Thread-lokalem Speicher auf getrennte Random-Instanzen zugreifen zu können, die untereinander nicht synchronisiert werden müssen. 

## Aufgaben ##

* Ändern Sie ``MyThreadLocalRandom`` so, dass jeder Thread ein getrenntes Random-Objekt hat, das mit ``now`` als _Seed_ konfiguriert wurde, damit jeweils in den 10 Threads dieselbe Sequenz von 20 Zufallszahlen gezogen wird. 

  - ``MyThreadLocalRandom0``: fehlerhaft: Es gibt genau eine Instanz von ``Random``, die von allen Threads geteilt benutzt wird, deshalb wird eine Sequenz von 200 unabhängigen Zufallszahlen gezogen.
  - ``MyThreadLocalRandom1``: korrekt: Ein ``Random``-Objekt wird beim Instanziieren von ``runner`` im Thread-lokalen Speicher vorbereitet. Sobald später aus mehreren Threads darauf zugegriffen wird, wird jeweils Thread-lokal eine neue Instanz von ``Random`` erzeugt, die alle denselben _Seed_ verwenden, deshalb wird in den 10 Threads jeweils dieselbe Sequenz von 20 Zufallszahlen gezogen. 
  - ``MyThreadLocalRandom2``: korrekt und besser verallgemeinerbar: Ein ``Random``-Objekt wird beim Laden der Klasse im Thread-lokalen Speicher vorbereitet. Sobald später aus mehreren Threads darauf zugegriffen wird, wird jeweils Thread-lokal eine neue Instanz von ``Random`` erzeugt, die alle denselben _Seed_ verwenden, deshalb wird in den 10 Threads jeweils dieselbe Sequenz von 20 Zufallszahlen gezogen. Besser verallgemeinerbar, falls es mehrere Instanzen des ``Runnable`` geben sollte.
  - ``MyThreadLocalRandom3``: korrekt, aber problematisch: Ein Thread-lokaler Zufallsgenerator wird beim Laden der Klasse vorbereitet. Sobald später aus mehreren Threads darauf zugegriffen wird, wird jeweils Thread-lokal auf eine Kopie davon zugegriffen, deshalb wird in den 10 Threads jeweils dieselbe Sequenz von 20 Zufallszahlen gezogen. Allerdings sollte auf ThreadLocalRandom.current() nicht static, also beim Laden zugegriffen werden, sondern in jeder Instanz.
  - ``MyThreadLocalRandom4``: korrekt und besser: hier wird per Instanz auf ThreadLocalRandom.current() zugegriffen.