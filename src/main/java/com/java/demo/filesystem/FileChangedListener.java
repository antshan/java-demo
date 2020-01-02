package com.java.demo.filesystem;

import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;

@FunctionalInterface
public interface FileChangedListener extends EventListener, Consumer<FileChangedEvent>, Observer {

    void onChanged(FileChangedEvent event);

    default void accept(FileChangedEvent event){
        this.onChanged(event);
    }

    default void update(Observable observable,Object event){
        if (event instanceof FileChangedEvent){
            onChanged((FileChangedEvent) event);
        }
    }
}
