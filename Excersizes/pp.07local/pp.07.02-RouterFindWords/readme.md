# Actors #

## Akka: Paralleles Suchen/Finden eines Suchstrings in Textdateien ##

Aufbauend auf der Musterlösung von pp.07.01-AkkaFindWords soll ein Actor-System mit der Verwendung eines Routers entwickelt werden.

## Aufgaben ##
* Bauen Sie einen Router ein, der beliebig viele Aufträge auf *n* ``WorkerActor`` verteilt und dabei die *RoundRobinRoutingLogic* verwendet.  
* Es empfiehlt sich die Routees, den Router und die Verbindung zwischen ihnen im Konstruktor von ``MasterActor`` zu erzeugen.
* Die Routees sind WorkerActor-Instanzen. Es ist sinnvoll, eine feste Anzahl von ihnen in einer Schleife zu erzeugen. 
* Als Router-Verhalten soll Round-Robin verwendet werden.
* Verdoppeln Sie die Anzahl der Dateien, in denen gesucht werden soll, indem Sie Kopien der vier gegebenen Dateien erstellen. Dafür muss auch ``Main`` geändert werden.
* Sehen Sie weniger Routees als Dateien vor, damit gezeigt werden kann, dass Ihr Programm mit einer festen Anzahl von WorkerActor-Instanzen eine beliebige Anzahl von (Teil-) Aufgaben lösen kann.  