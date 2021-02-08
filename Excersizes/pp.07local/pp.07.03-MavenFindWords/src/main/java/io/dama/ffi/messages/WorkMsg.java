package io.dama.ffi.messages;

public class WorkMsg {
    private final String filename;
    private final String searchword;

    public WorkMsg(final String filename, final String seachword) {
        this.filename = filename;
        this.searchword = seachword;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getSearchword() {
        return this.searchword;
    }
}