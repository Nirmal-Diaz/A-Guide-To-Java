package io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

class WatchServiceAPI {
    //Use "watchServiceDirectory" as the default directory
    void demonstrateWatcher(Path directory) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            WatchKey watchKey;
            while ((watchKey = watchService.take()) != null) {
                for (WatchEvent<?> event: watchKey.pollEvents()) {
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context());
                }
                //NOTE: If the WatchKey is no longer valid, the directory is inaccessible so exit the loop
                boolean valid = watchKey.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException ioe) {
            System.out.println("IOException occurred");
        } catch (InterruptedException ie) {
            System.out.println("InterruptedException occurred");
        }
    }
}