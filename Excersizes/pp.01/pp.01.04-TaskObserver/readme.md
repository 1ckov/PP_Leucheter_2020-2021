# Das Thread-Konzept von Java #

## Umsetzung des _Observer Patterns_ ##

In ``ITask`` und ``ITaskObserver`` im Package ``io.dama.ffi.threads.model`` sind Interface-Spezifikationen für ein Mini-Framework zum Umgang mit _Tasks_. Das Framework folgt dem _Observer_ Entwurfsmuster: Ein _Task_ ist in diesem Framework ein **Observable**. Das bedeutet, dass sich **Observer** bei ihm registrieren können. Wird der Zustand des **Observable** geändert, werden alle **Observer**, die sich bei dem geänderten **Observable** registriert hatten, über die Änderung informiert. Dazu ruft der geänderte _Task_ die Methode ``inform(...)`` bei allen registrierten **Observern** auf.

In der Klasse ``Task`` ist ein Gerüst für die Implementierung des ``ITask``-Interfaces vorgegeben. Die **Observer** brauchen nicht selber programmiert werden. Sie sind bereits im Package ``pp.threads.run`` vorhanden und sollten bereits über das JAR in ``lib/ppThreadsRun.jar`` in das Projekt eingebunden sein.

In der Klasse ``Task`` ist eine ``main``-Methode, die 100 ``Task``-Objekte erzeugt, sie in einem Array ablegt und an eine Methode aus dem JAR übergibt. Dort werden jeweils mehrere **Observer** für jeden ``Task`` erzeugt und durch eine Testprozedur geführt.

## Aufgaben ##

* Verwenden Sie zur Lösung keine weiteren (neben den bereits angegebenen) Imports: Implementieren Sie ``Task`` und lassen Sie die ``main``-Methode ablaufen. Achten Sie auf etwaige Exceptions und korrigieren Sie Ihre Implementierung, bis es auch nach längerem Laufenlassen der ``main``-Methode keine Exceptions gibt.
* Ändern Sie den Import in ``Task`` von 

		import io.dama.ffi.threads.run.Runner;

zu 

		import io.dama.ffi.threads.run.synch.Runner;

* Lassen Sie die ``main``-Methode ablaufen. Achten Sie auf etwaige Exceptions und korrigieren Sie Ihre Implementierung, bis es auch nach längerem Laufenlassen der ``main``-Methode keine Exceptions gibt.