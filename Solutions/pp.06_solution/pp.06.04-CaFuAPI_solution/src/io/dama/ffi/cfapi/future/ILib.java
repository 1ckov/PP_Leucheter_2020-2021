package io.dama.ffi.cfapi.future;

import java.util.concurrent.Future;

public interface ILib extends io.dama.ffi.cfapi.ILib {
    public Future<Integer> calcAsync();
}
