# Locks #

## Vergleich von ``ReentrantLock``, ``ReentrantReadWriteLock``, ``StampedLock`` und ``synchronized`` ##


## Aufgaben ##
* Bilden Sie von der abstrakten Klasse ``Experiment`` Unterklassen namens ``ExpSynchronized``, ``ExpReentrantLock``, ``ExpReadWriteLock`` und ``ExpStampedLock``. Überschreiben Sie die abstrakten Methoden 

	public abstract void incCounter();
	public abstract int getCounter();

jeweils mit threadsicheren Implementierungen, die entweder ``synchronized``, ``ReentrantLock``, ``ReentrantReadWriteLock`` oder ``StampedLock`` zur Koordination des nebenläufigen Zugriffs auf die Instanzvariable ``counter`` verwenden. 

* Verwenden Sie die ``main``-Methode wie in ``ExpUnsynchronized``, also

	public static void main(final String... args) throws InterruptedException {
        (new ExpUnsynchronized()).experimentPar();
    }

oder eine vergleichbare Funktionalität, um die Laufzeit mit zwei Threads und einem steuerbaren Verhältnis von lesenden und ändernden Zugriffen zu analysieren.

* Führen Sie Messungen der Laufzeit durch.
* Vergleichen Sie auch das Zeitverhalten für ``ReentrantLock(false)`` und ``ReentrantLock(true)`` sowie analog bei ``ReentrantReadWriteLock(false)`` und ``ReentrantReadWriteLock(true)``.

