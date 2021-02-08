package io.dama.ffi.messages;

import java.util.Collections;
import java.util.List;

public class ResultMsg {
    private final List<String> result;

    public ResultMsg(final List<String> result) {
        this.result = Collections.unmodifiableList(result);
    }

    public List<String> getResult() {
        return this.result;
    }
}