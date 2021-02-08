# Completable Future #

## asynchrone APIs ##


## Lösungsskizze ##

* Probieren Sie ``io.dama.ffi.cfapi.calcSync()`` aus, indem Sie ``User`` laufen lassen 

=> Es wird nur der Main-Thread benutzt. 
  
* Entwerfen Sie ein neues Package ``io.dama.ffi.cfapi.future`` mit Interface und Implementierung eines zusätzlichen Aufrufs ``calcAsync()``. Dieser Aufruf soll ``calc.Sync`` verpacken und ein Future zurückgeben, das in einem eigenen Thread-Pool ausgeführt wird:

	Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1)
	
=> die Lösung verwendet neben dem Main-Thread leider nur genau einen Thread aus dem Thread-Pool, da jeweils mit get() auf das Ende des jeweils vorigen Threads gewartet wird.  
	
* Entwerfen Sie ein neues Package ``io.dama.ffi.cfapi.completablefuture`` mit Interface und Implementierung eines zusätzlichen Aufrufs ``calcAsync()``. Dieser Aufruf soll ``calc.Sync`` in einem CompletableFuture verpacken und im *commonPool* ausführen.

=> hier arbeiten die Thread aus dem *commonPool* nebeneinander. man muss mit ``join()`` auf das Ende aller CompletableFutures warten. 
  