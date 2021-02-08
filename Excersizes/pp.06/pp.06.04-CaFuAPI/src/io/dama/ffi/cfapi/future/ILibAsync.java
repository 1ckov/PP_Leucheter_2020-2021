package io.dama.ffi.cfapi.future;

import java.util.concurrent.Future;

public interface ILibAsync {
   public Future<Integer> calcAsync() throws Exception; 
}
