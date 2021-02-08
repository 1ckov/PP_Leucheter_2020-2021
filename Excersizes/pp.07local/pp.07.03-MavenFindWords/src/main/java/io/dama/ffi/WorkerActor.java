package io.dama.ffi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import akka.actor.AbstractActor;
import io.dama.ffi.messages.PleaseCleanupAndStop;
import io.dama.ffi.messages.ResultMsg;
import io.dama.ffi.messages.WorkMsg;

public class WorkerActor extends AbstractActor {
    private Path path;
    private Pattern searchPattern;

    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(WorkMsg.class, this::handleWorkMsg) //
                .match(PleaseCleanupAndStop.class, (m) -> getContext().stop(getSelf())) //
                .build();
    }

    private void handleWorkMsg(final WorkMsg msg) throws IOException {
        this.path = Paths.get(msg.getFilename());
        this.searchPattern = Pattern.compile(".*\\b" + msg.getSearchword() + "\\b.*");
        var result = search();
        getSender().tell(new ResultMsg(result), getSelf());
    }

    public List<String> search() throws IOException {
        var result = new ArrayList<String>();
        var lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
        var count = 0;
        for (var line : lines) {
            count++;
            if (this.searchPattern.matcher(line).matches()) {
                result.add(this.path + " " + count + " : " + line);
            }
        }
        return result;
    }
}
