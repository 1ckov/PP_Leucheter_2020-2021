# Completable Future

## Monte-Carlo-Algorithmus zur Annäherung von π

Monte-Carlo-Algorithmen sind randomisierte Algorithmen, die durch fortgesetzte Wahl von Zufallszahlen versuchen, ein Ergebnis anzunähern. Es gibt bei ihnen aber keine Garantie für Korrektheit.

Ein bekannter Monte-Carlo-Algorithmus zur Annäherung von π basiert darauf, dass zufällig Punkte `(x y)` im Koordinatenbereich von `(0, 0)` bis `(1, 1)` gezogen werden. Für jeden gezogenen Punkt wird vermerkt, ob er innerhalb eines Einheitsviertelkreises (Radius=1) liegt. Durch Anwendung des Satzes des Pythagoras, kann dies geprüft werden.

1. Im folgenden Fall wurde zufällig ein Punkt mit den Koordinaten `x` und `y` gewählt, so dass gilt: `sqrt(x*x + y*y) = 1`. Der Punkt liegt also zufällig genau auf dem Kreisbogen.<br/>
![sqrt(x*x + y*y) = 1](doc/on.png "sqrt(x*x + y*y) = 1") 

2. Im nächsten Fall wurde zufällig ein Punkt mit den Koordinaten `x` und `y` gewählt, so dass gilt: `sqrt(x*x + y*y) < 1`. Der Punkt liegt also zufällig innerhalb des Kreisbogens.<br/>
![sqrt(x*x + y*y) < 1](doc/in.png "sqrt(x*x + y*y) < 1") 

3. Im letzten Fall wurde zufällig ein Punkt mit den Koordinaten `x` und `y` gewählt, so dass gilt: `sqrt(x*x + y*y) > 1`. Der Punkt liegt also zufällig außerhalb des Kreises.<br/>
![sqrt(x*x + y*y) > 1](doc/out.png "sqrt(x*x + y*y) > 1") 

Werden sehr viele Punkte gezogen, die sich innerhalb der Koordinaten `(0, 0)` und `(1, 1)` gleichmäßig verteilen, sollte sich für das Verhältnis der Anzahl der Punkte innerhalb des Kreises (einschließlich auf dem Kreisbogen) zu allen innerhalb des Quadrats das Verhältnis der Fläche des Viertelkreises (Radius 1) zur Fläche des Quadrats (Kantenlänge 1) ergeben.


Anzahl der Punkte innerhalb und auf dem Kreis (`sqrt(x*x + y*y) <= 1`) / Anzahl aller Punkte = (π r^2 / 4) / r^2

Da `r=1` kann π angenähert werden als `pi = 4.0 * in / (in + out)`, wobei `in` die Anzahl der Punkte innerhalb des Kreises ist, für die gilt `sqrt(x*x + y*y) <= 1` und `out` die restlichen, die außerhalb des Kreises liegen. 

Falls die zufällig gezogenen Punkte gleichmäßig über die Fläche verteilt sind, nähert sich `pi` der [Konstante π](https://3.141592653589793238462643383279502884197169399375105820974944592.eu/)
 an. Es liegt in der Natur der Sache von Monte-Carlo-Algorithmen, dass dies nicht in jedem Fall zu einem korrekten Ergebnis führt. Je mehr Punkte gezogen werden, desto sollte im Mittel der Wert von π angenähert werden. Es ist also sinnvoll, so viele Punkte wie möglich zu ziehen. 
 
* `TOTAL_CYCLES` gibt an, wieviele Punkte insgesamt gezogen werden sollen. Das ist sozusagen das Rechenziel. 
* Die Klasse ``InOutTuple`` dient der Repräsentation der Summe der Würfe innerhalb des Kreises (Property ``in``) und außerhalb des Kreises (Property ``out``). 

## Lösungsskizze
* Ändern Sie die Berechung von sequentiell zu nebenläufig, indem Sie einen ``ExecutorService`` der Art ``FixedThreadPool`` erzeugen und Teilaufgaben zur nebenläufigen Lösung dort bearbeiten lassen.

  * Als Thread-Pool wird ein ``FixedThreadPool`` mit folgendem Aufruf erzeugt. Die Größe wird hier der Übersichtlichkeit halber als Konstante ``PARALLELISM`` angegeben:
  
	var pool = Executors.newFixedThreadPool(PARALLELISM);
  	  
  * Zum Sammeln der Ergebnisse, die in Form von ``Future<InOutTuple>`` vorliegen, wird eine ``LinkedList`` verwendet. Sie wird in einer Schleife mit ``Future``´s gefüllt, die durch ``submit``-Aufrufe am ``FixedThreadPool`` erzeugt werden.
  
	var futures = new LinkedList<Future<InOutTuple>>();
  
  * Nachdem alle ``submit``-Aufrufe abgesetzt wurden, muss der Pool heruntergefahren werden (``shutdown``). Die anstehenden ``Callable``'s werden noch zu Ende gebracht, dann werden die Threads des Thread-Pools beendet. Die Anzahl der Durchläufe in jedem ``Callable`` kann jetzt durch ``PARALLELISM`` geteilt werden, da genau so viele Berechnungen im Pool durchgeführt werden.
  
	for (var i = 0; i < PARALLELISM; i++) {
		futures.add(pool.submit(() -> getResultMonteCarloPiDraw(TOTAL_CYCLES / PARALLELISM)));
	}
	pool.shutdown();
  
* Da es erforderlich ist, mehrere ``InOutTuple`` miteinander (die Ergebnisse der Teilaufgaben) zu verrechnen, muss der Klasse ``InOutTuple`` eine Methode zum Addieren zweier ``InOutTuple``  hinzugefügt werden. Dafür muss jeweils das ``in``- und das ``out``-Attribut addiert werden.  

  * Um zwei ``InOutTuple``-Objekte miteinander zu addieren, wird die folgende Methode zu ``InOutTuple`` hinzugefügt. Das Ergebnis ist immer ein neues ``InOutTuple``-Objekt, das die Summe der zwei addierten ``InOutTuple``-Objekte (this und other) repräsentiert:
  
	public InOutTuple add(final InOutTuple other) {
		return new InOutTuple(getIn() + other.getIn(), getOut() + other.getOut());
	}
  
  * Die Ergebnisse können damit ganz einfach in einer Schleife zusammengefasst werden. Das erste get bezieht sich immer auf die ``LinkedList``, das zweite auf die Inhaltselemente der ``LinkedList`` also ``Future<InOutTuple>``-Objekte. Das Ergebnis des jeweils zweiten ``get`` ist also vom Typ ``InOutTuple``.  
  
	var result = futures.get(0).get();
	for (var i = 1; i < PARALLELISM; i++) {
		result = result.add(futures.get(i).get());
	}

* Variieren Sie die Größe des Thread-Pools und lassen Sie das Programm jeweils mehrfach laufen. Vergleichen Sie die erforderliche Zeitdauer mit der für die sequentielle Berechnung.
 
