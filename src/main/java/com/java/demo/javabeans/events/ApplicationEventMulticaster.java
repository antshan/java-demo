package com.java.demo.javabeans.events;

public class ApplicationEventMulticaster {

    private final ApplicationEventListenerRegistry registry;

    public ApplicationEventMulticaster(){
        this.registry = new SimpleApplicationEventListenerRegistry();
    }

    public ApplicationEventMulticaster(ApplicationEventListenerRegistry registry){
        this.registry = registry;
    }

    public void addApplicationEventListener(ApplicationEventListener<?> listener){
        registry.addApplicationEventListener(listener);
    }

    public void removeApplicationEventListener(ApplicationEventListener<?> listener){
        registry.removeApplicationEventListener(listener);
    }

    public ApplicationEventListener[] getApplicationEventListeners(){
        return registry.getApplicationEventListeners();
    }

    public ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> type){
        return registry.getApplicationEventListeners(type);
    }


    public void multicast(ApplicationEvent event){
        for (ApplicationEventListener listener :registry.getApplicationEventListeners(event.getClass())){
            listener.onEvent(event);
        }
    }


    public static void main(String[] args) {
        displaySimpleApplicationEventListener();
        displayGenericApplicationEventListener();
    }

    public static void displayGenericApplicationEventListener(){
        ApplicationEventMulticaster multicaster = new ApplicationEventMulticaster(new GenericApplicationListenerRegistry());

        multicaster.addApplicationEventListener(new MyEventListener());

        multicaster.addApplicationEventListener(new MyEventListener2());

        multicaster.multicast(new MyEvent("hello world!"));
    }

    public static void displaySimpleApplicationEventListener(){

        ApplicationEventMulticaster applicationEventMulticaster = new ApplicationEventMulticaster();

        applicationEventMulticaster.addApplicationEventListener(event -> {
            System.out.println("处理事件-1: "+event);
        });

        applicationEventMulticaster.addApplicationEventListener(event -> {
            System.out.println("处理事件-2: "+event);
        });

        applicationEventMulticaster.addApplicationEventListener(event -> {
            System.out.println("处理事件-3: "+event);
        });

        applicationEventMulticaster.multicast(new ApplicationEvent("hello world!"));
    }
}
