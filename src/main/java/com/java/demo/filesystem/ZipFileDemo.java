package com.java.demo.filesystem;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileDemo {
    public static void main(String[] args) {
        Class clazz = IOUtils.class;
        URL zipFileUrl = clazz.getProtectionDomain().getCodeSource().getLocation();
        try(ZipFile zipFile = new ZipFile(zipFileUrl.getPath(), Charsets.toCharset("UTF-8"))){
            ZipEntry zipEntry = zipFile.getEntry("META-INF/MANIFEST.MF");
            try(InputStream inputStream = zipFile.getInputStream(zipEntry)) {
                System.out.println(IOUtils.toString(inputStream, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
