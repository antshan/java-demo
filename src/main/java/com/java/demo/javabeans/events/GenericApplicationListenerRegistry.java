package com.java.demo.javabeans.events;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 支持泛型的时间监听器注册中心
 */
public class GenericApplicationListenerRegistry implements ApplicationEventListenerRegistry {

    private Map<String, List<ApplicationEventListener<?>>> genericListeners = new LinkedHashMap<>();


    @Override
    public void addApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        List<ApplicationEventListener<?>> listeners = getListeners(applicationEventListener);
        listeners.add(applicationEventListener);
    }

    public List<ApplicationEventListener<?>> getListeners(ApplicationEventListener<?> applicationEventListener){
        Class listenerClass = applicationEventListener.getClass();
        Type[]  genericInterfaces = listenerClass.getGenericInterfaces();
        String eventType = Arrays.stream(genericInterfaces).filter(t-> t instanceof ParameterizedType)
                .map(type -> (ParameterizedType) type)
                .filter(parameterizedType -> ApplicationEventListener.class.equals(parameterizedType.getRawType()))
                .map(parameterizedType -> {
                    return parameterizedType.getActualTypeArguments()[0].getTypeName();
                })
                .findFirst()
                .orElse(null);
        return genericListeners.computeIfAbsent(eventType,k->new LinkedList<>());
    }

    @Override
    public void removeApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        List<ApplicationEventListener<?>> listeners = getListeners(applicationEventListener);
        listeners.remove(applicationEventListener);
    }

    @Override
    public ApplicationEventListener[] getApplicationEventListeners() {
        return new ApplicationEventListener[0];
    }

    @Override
    public ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> type) {
        String eventName = type.getName();
        return genericListeners.getOrDefault(eventName,Collections.emptyList()).toArray(new ApplicationEventListener[0]);
    }
}
