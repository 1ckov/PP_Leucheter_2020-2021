package io.dama.ffi.messages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindMsg {
    private final List<String> fileNames;
    private final String searchword;

    public FindMsg(final String[] filenames, final String searchword) {
        this.fileNames = Collections.unmodifiableList(Arrays.asList(filenames));
        this.searchword = searchword;
    }

    public List<String> getFilenames() {
        return this.fileNames;
    }

    public String getSearchword() {
        return this.searchword;
    }
}