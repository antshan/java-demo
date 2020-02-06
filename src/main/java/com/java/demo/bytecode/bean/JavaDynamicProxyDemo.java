package com.java.demo.bytecode.bean;

import java.beans.*;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaDynamicProxyDemo {

    public static void main(String[] args) throws Exception {
        Person person = new Person();

        Nameable nameable = newInstance(person);

        nameable.setName("张三");
        nameable.setName("zhangsan");
        nameable.setName("463780234");
    }

    static Nameable newInstance(Person person) {

        PublishingPropertyChangeEventInvocationHandler handler =
                new PublishingPropertyChangeEventInvocationHandler(person);

        handler.addPropertyChangeListener(evt -> {
            System.out.printf("属性[%s]发生修改，旧值：%s，新值：%s。\n",
                    evt.getPropertyName(),
                    evt.getOldValue(),
                    evt.getNewValue());
        });

        handler.addVetobaleChangeListener(evt -> {
            if (isNumberic((String) evt.getNewValue())){
                throw new PropertyVetoException(String.format("属性[%s]修改异常，新值[%s]不能为纯数字。\n",
                        evt.getPropertyName(),
                        evt.getNewValue()),evt);
            }
        });

        ClassLoader classLoader = JavaDynamicProxyDemo.class.getClassLoader();

        return (Nameable) Proxy.newProxyInstance(classLoader,new Class[]{Nameable.class, Serializable.class},handler);
    }

    static class PublishingPropertyChangeEventInvocationHandler implements InvocationHandler {

        private final Person person;

        private final PropertyChangeSupport propertyChangeSupport;

        private final VetoableChangeSupport vetoableChangeSupport;

        public PublishingPropertyChangeEventInvocationHandler(Person person) {
            this.person = person;
            this.propertyChangeSupport = new PropertyChangeSupport(person);
            this.vetoableChangeSupport = new VetoableChangeSupport(person);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String propertyName = "name";
            if (method.getReturnType().equals(void.class) &&
                    "setName".equals(method.getName()) &&
                    args.length == 1 &&
                    args[0] instanceof String) {

                String oldValue = this.person.getName();
                String newValue = (String) args[0];

                fireVetoableChange(propertyName, oldValue, newValue);

                this.person.setName(newValue);

                firePropertyChange(propertyName, oldValue, newValue);

            }
            return null;
        }

        public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
            propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
        }

        public void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws PropertyVetoException {
            vetoableChangeSupport.fireVetoableChange(propertyName, oldValue, newValue);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void addVetobaleChangeListener(VetoableChangeListener listener) {
            vetoableChangeSupport.addVetoableChangeListener(listener);
        }

        public PropertyChangeListener[] getPropertyChangeListeners() {
            return propertyChangeSupport.getPropertyChangeListeners();
        }

        public VetoableChangeListener[] getVetoableChangeListeners() {
            return vetoableChangeSupport.getVetoableChangeListeners();
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.removePropertyChangeListener(listener);
        }

        public void removeVetoableChangeListener(VetoableChangeListener listener) {
            vetoableChangeSupport.removeVetoableChangeListener(listener);
        }
    }

    public static boolean isNumberic(String string){
        if (string == null){
            return false;
        }

        for (int i =0;i<string.length();i++){
            if (!Character.isDigit(string.charAt(i))){
                return false;
            }
        }

        return true;
    }
}
