package com.java.demo.javabeans.properties;

import java.beans.PropertyVetoException;

import static com.java.demo.javabeans.properties.Person.isNumeric;

public class PersonDemo {

    public static void main(String[] args) throws PropertyVetoException {
        Person person = new Person();

        person.addPropertyChangeSupportListener(evt -> {
            System.out.printf("属性 [%s] 内容发生变化，事件来源[%s], 旧值: %s, 新值: %s\n",
                    evt.getPropertyName(),
                    evt.getSource(),
                    evt.getOldValue(),
                    evt.getNewValue());
        });

        person.addVetoableChangeSupportListener(evt -> {
            String newValue = String.valueOf(evt.getNewValue());
            if (isNumeric(newValue)){
                throw new PropertyVetoException(
                        String.format("当前属性[%s]的新值[%s]不合法，不能是纯数字",evt.getPropertyName(),evt.getNewValue()), evt);
            }
        });

        person.setName("Java Beans");
        System.out.println("当前Person对象的name属性是："+person.getName());

        person.setName("勉强属性");
        System.out.println("当前Person对象的name属性是："+person.getName());

        person.setName("1234");
        System.out.println("当前Person对象的name属性是："+person.getName());
    }
}
