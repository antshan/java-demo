package com.java.demo.javabeans.customization;

import java.beans.PropertyEditorSupport;

import static com.java.demo.javabeans.properties.Person.isNumeric;

public class IdPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (isNumeric(text)){
            setValue(Long.valueOf(text));
        }
    }

    @Override
    public Long getValue() {
        return (Long) super.getValue();
    }
}
