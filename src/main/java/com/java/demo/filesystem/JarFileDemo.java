package com.java.demo.filesystem;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class JarFileDemo {

    public static void main(String[] args) {
        Class clazz = IOUtils.class;
        URL jarFileUrl = clazz.getProtectionDomain().getCodeSource().getLocation();
        try(JarFile jarFile = new JarFile(jarFileUrl.getPath())){
            Manifest manifest = jarFile.getManifest();
            System.out.println(new HashMap<>(manifest.getMainAttributes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
