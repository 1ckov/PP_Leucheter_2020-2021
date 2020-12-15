# Parallele Programmierung. Konkurrierender Zugriff #

## Anwendung von Memory Barrieren ##

Durch das Setzen von ``stopped`` soll der ``MemoryBarrierTest``-Thread kontrolliert beendet werden, indem die ``while``-Schleife und damit die ``run()``-Methode des Threads terminiert. 

Wenn ``stopped`` aber aus einem anderen Thread heraus geändert wird, kann es sein, dass die Speicheränderung lokal im Speicher-Cache des ändernden Threads verbleibt und dem ``MemoryBarrierTest``-Thread nicht übermittelt wird. Auf einem 1-Core-System wird dies zwar nie passieren, denn bei jedem Kontextwechsel durch den Scheduler wird auch der Thread-lokale Speicher-Cache synchronisiert, aber auf Mehr-Core-Systemen wird es wahrscheinlich dazu kommen, dass der Thread nicht korrekt terminiert.. 

## Aufgaben ##

* Ändern Sie das Programm so, dass der Thread von MemoryBarrierTest nach 1 Sek. sicher und auf eine geordnete Weise beendet wird.

 - MemoryBarrierTest1: Variable als volatile kennzeichnen. 
 - MemoryBarrierTest2: synchronized-Keyword (gegenseitiges Warten)
 - MemoryBarrierTest5: Ende eines Threads
 - MemoryBarrierTest6: Zustandsänderung eines Threads
 
   _Hinweis: Die Nummern korrespondieren zu der Reihenfolge der Möglichkeiten für Memory Barrierts auf den Folien._  