package com.java.demo.javabeans.events;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 简单事件注册中心 (不支持事件泛型)
 */
public class SimpleApplicationEventListenerRegistry implements ApplicationEventListenerRegistry {

    private Set<ApplicationEventListener> listeners = new TreeSet<ApplicationEventListener>(new Comparator<ApplicationEventListener>() {
        @Override
        public int compare(ApplicationEventListener o1, ApplicationEventListener o2) {
            return o1.getClass().getName().compareTo(o2.getClass().getName());
        }
    });

    @Override
    public void addApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        listeners.add(applicationEventListener);
    }

    @Override
    public void removeApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        listeners.remove(applicationEventListener);
    }

    @Override
    public ApplicationEventListener[] getApplicationEventListeners() {
        return new ApplicationEventListener[0];
    }

    @Override
    public ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> type) {
        return listeners.toArray(new ApplicationEventListener[0]);
    }
}
