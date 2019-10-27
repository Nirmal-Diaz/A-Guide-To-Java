package io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

class WatchServiceAPI {
    //Use "watchServiceDirectory" as the default directory
    @SuppressWarnings("unchecked")
    void demonstrateWatcher(Path directory) {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        try {
            WatchKey key = directory.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException x) {
            System.err.println("IOException occurred");
        }

        for (;;) {
            //Wait for key to be signaled
            WatchKey watchKey;
            try {
                watchKey = watcher.take();
            } catch (InterruptedException x) {
                System.out.println("InterruptedException occurred");
                return;
            }
        
            for (WatchEvent<?> event: watchKey.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                //This key is registered only for ENTRY_CREATE events, but an OVERFLOW event can occur regardless if events are lost or discarded.
                if (kind == OVERFLOW) {
                    continue;
                }
                //The filename is the context of the event
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();
        
                //Verify that the new file is a text file.
                try {
                    // Resolve the filename against the directory
                    Path child = directory.resolve(filename);
                    if (!Files.probeContentType(child).equals("text/plain")) {
                        System.err.format("New file '%s'" + " is not a plain text file.%n", filename);
                        continue;
                    }
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
                }
            }
        
            // Reset the key (This step is critical if you want to receive further watch events)
            //If the key is no longer valid, the directory is inaccessible so exit the loop.
            boolean valid = watchKey.reset();
            if (!valid) {
                break;
            }
        }
    }
}