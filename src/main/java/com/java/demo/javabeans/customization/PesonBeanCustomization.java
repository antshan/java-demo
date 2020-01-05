package com.java.demo.javabeans.customization;

import com.java.demo.javabeans.properties.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditor;
import java.util.Arrays;
import java.util.Date;

public class PesonBeanCustomization {

    public static void main(String[] args) throws Exception {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        Person personBean = new Person();
        Arrays.stream(beanInfo.getPropertyDescriptors())
                .filter(propertyDescriptor -> "id".equals(propertyDescriptor.getName()))
                .findFirst()
                .ifPresent(idPropertyEditor->{
                    idPropertyEditor.setPropertyEditorClass(IdPropertyEditor.class);
                    PropertyEditor propertyEditor = idPropertyEditor.createPropertyEditor(personBean);

                    propertyEditor.addPropertyChangeListener(evt -> {
                        personBean.setId((Long) propertyEditor.getValue()
                        );
                    });
                    propertyEditor.setAsText("1");
                });

        Arrays.stream(beanInfo.getPropertyDescriptors())
                .filter(propertyDescriptor -> "updateTime".equals(propertyDescriptor.getName()))
                .findFirst()
                .ifPresent(updateTimePropertyEditor->{
                    updateTimePropertyEditor.setPropertyEditorClass(UpdateTimePropertyEditor.class);
                    PropertyEditor propertyEditor = updateTimePropertyEditor.createPropertyEditor(personBean);

                    propertyEditor.addPropertyChangeListener(evt -> {
                        personBean.setUpdateTime((Date) propertyEditor.getValue());
                    });

                    propertyEditor.setAsText("2020-02-02 20:00:02");
                });

        System.out.println("Person ¿‡: "+ personBean);
    }
}
