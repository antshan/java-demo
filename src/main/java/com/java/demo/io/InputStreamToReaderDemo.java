package com.java.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class InputStreamToReaderDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {

        InputStream inputStream = System.in;

        //Cast InputStream to InputStreamReader
        InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
    }
}
