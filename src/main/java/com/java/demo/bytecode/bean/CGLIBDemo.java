package com.java.demo.bytecode.bean;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.beans.*;
import java.lang.reflect.Method;

import static com.java.demo.bytecode.bean.JavaDynamicProxyDemo.isNumberic;

public class CGLIBDemo {

    public static void main(String[] args) throws Exception {

        Person person = newInstance(Person.class);

        person.setName("张三");
        person.setName("zhangsan");
        person.setName("463780234");
    }

    public static Person newInstance(Class<?> classObject){
        Enhancer enhancer = new Enhancer();
        PublishingPropertyChangeMethodInterceptor methodInterceptor =
                new PublishingPropertyChangeMethodInterceptor();
        methodInterceptor.addPropertyChangeListener(evt -> {
            System.out.printf("属性[%s]发生修改，旧值：%s，新值：%s。\n",
                    evt.getPropertyName(),
                    evt.getOldValue(),
                    evt.getNewValue());
        });

        methodInterceptor.addVetobaleChangeListener(evt -> {
            if (isNumberic((String) evt.getNewValue())){
                throw new PropertyVetoException(String.format("属性[%s]修改异常，新值[%s]不能为纯数字。\n",
                        evt.getPropertyName(),
                        evt.getNewValue()),evt);
            }
        });
        enhancer.setSuperclass(classObject);
        enhancer.setCallback(methodInterceptor);

        Object enhancerBean = enhancer.create();
        return (Person) enhancerBean;
    }

    static class PublishingPropertyChangeMethodInterceptor implements MethodInterceptor{

        private final transient PropertyChangeSupport propertyChangeSupport;

        private final transient VetoableChangeSupport vetoableChangeSupport;

        public PublishingPropertyChangeMethodInterceptor(){
            this.propertyChangeSupport = new PropertyChangeSupport(this);
            this.vetoableChangeSupport = new VetoableChangeSupport(this);
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

            String methodName = method.getName();
            if (method.getReturnType().equals(void.class) &&
                    "setName".equals(method.getName()) &&
                    args.length == 1 &&
                    args[0] instanceof String) {

                String propertyName = "name";
                Person person = (Person)o;
                String oldValue = person.getName();
                String newValue = (String) args[0];

                fireVetoableChange(propertyName, oldValue, newValue);

                methodProxy.invokeSuper(o,args);

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
}
