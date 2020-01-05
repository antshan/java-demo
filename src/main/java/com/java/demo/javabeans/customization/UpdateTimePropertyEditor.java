package com.java.demo.javabeans.customization;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateTimePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            setValue(simpleDateFormat.parse(text));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
