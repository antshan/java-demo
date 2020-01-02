package com.java.demo.filesystem;

import java.io.File;
import java.util.Observable;

public class FileChangedEventPublisher extends Observable {

    public void publish(File changedFile){publish(new FileChangedEvent(changedFile));}

    private void publish(FileChangedEvent event) {
        super.setChanged();
        super.notifyObservers(event);
    }

    public void addFileChangedListener(FileChangedListener listener){
        addObserver(listener);
    }

    public static void main(String[] args) {
        FileChangedEventPublisher publisher = new FileChangedEventPublisher();

        publisher.addFileChangedListener(event -> {
            System.out.println("处理文件变化事件"+ event);
        });

        publisher.publish(new File("temp"));
    }
}
