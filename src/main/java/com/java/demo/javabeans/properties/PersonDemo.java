package com.java.demo.javabeans.properties;

import java.beans.PropertyVetoException;

import static com.java.demo.javabeans.properties.Person.isNumeric;

public class PersonDemo {

    public static void main(String[] args) throws PropertyVetoException {
        Person person = new Person();

        person.addPropertyChangeSupportListener(evt -> {
            System.out.printf("���� [%s] ���ݷ����仯���¼���Դ[%s], ��ֵ: %s, ��ֵ: %s\n",
                    evt.getPropertyName(),
                    evt.getSource(),
                    evt.getOldValue(),
                    evt.getNewValue());
        });

        person.addVetoableChangeSupportListener(evt -> {
            String newValue = String.valueOf(evt.getNewValue());
            if (isNumeric(newValue)){
                throw new PropertyVetoException(
                        String.format("��ǰ����[%s]����ֵ[%s]���Ϸ��������Ǵ�����",evt.getPropertyName(),evt.getNewValue()), evt);
            }
        });

        person.setName("Java Beans");
        System.out.println("��ǰPerson�����name�����ǣ�"+person.getName());

        person.setName("��ǿ����");
        System.out.println("��ǰPerson�����name�����ǣ�"+person.getName());

        person.setName("1234");
        System.out.println("��ǰPerson�����name�����ǣ�"+person.getName());
    }
}
