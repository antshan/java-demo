package com.java.demo.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.*;

public class FileMonitor {

    private Map<File, Long> fileSnapshot = new HashMap<>();

    private ScheduledExecutorService pollingWatchingService = Executors.newScheduledThreadPool(1);

    private FileChangedEventPublisher publisher = new FileChangedEventPublisher();

    public void monitor(File monitoredFile){
        updateLastModifiedSnapshot(monitoredFile);

        pollingWatchingService.scheduleAtFixedRate(()->{
            Long lastModified = monitoredFile.lastModified();
            Long previousModified = fileSnapshot.putIfAbsent(monitoredFile,lastModified);

            if (lastModified > previousModified){
                publisher.publish(monitoredFile);
                fileSnapshot.put(monitoredFile,lastModified);
            }
        },0,5L, TimeUnit.SECONDS);

    }

    private void updateLastModifiedSnapshot(File monitoredFile) {
        fileSnapshot.put(monitoredFile,monitoredFile.lastModified());
    }

    public void addListener(FileChangedListener listener){
        publisher.addFileChangedListener(listener);
    }

    public static void main(String[] args) throws Exception {
        FileMonitor fileMonitor = new FileMonitor();

        fileMonitor.addListener(event -> {
            System.out.println("文件发生变化"+ event);
        });

        List<String> strings = new ArrayList<>(Arrays.asList("Hello","SF","World","Tencent"));
        File file = new File("D:\\temp.txt");
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(strings);
            outputStream.flush();
        }
        fileMonitor.monitor(file);
    }

}
