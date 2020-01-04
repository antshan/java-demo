package com.java.demo.javabeans.properties;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    private static final long serialVersionUID = -551188913709789300L;

    private Long id;

    private String name;

    private int age;

    private Date updateTime;

    public final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public final transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws PropertyVetoException {
        String propertyName = "name";
        String oldValue = this.name;
        String newValue= name;

        vetoableChangeSupport.fireVetoableChange(propertyName, oldValue, newValue);
        this.name = name;

        propertyChangeSupport.firePropertyChange(propertyName,oldValue,newValue);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void addPropertyChangeSupportListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeSupportListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public PropertyChangeListener[] getPropertyChangeSupportListeners(){
        return propertyChangeSupport.getPropertyChangeListeners();
    }

    public void addVetoableChangeSupportListener(VetoableChangeListener listener){
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeSupportListener(VetoableChangeListener listener){
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", updateTime=" + updateTime +
                '}';
    }

    public  static boolean isNumeric(String str){
        if (str == null){
            return false;
        }
        for (int i=0 ;i<str.length();i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
