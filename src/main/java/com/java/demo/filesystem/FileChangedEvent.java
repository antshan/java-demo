package com.java.demo.filesystem;

import java.util.EventObject;

public class FileChangedEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FileChangedEvent(Object source) {
        super(source);
    }
}
