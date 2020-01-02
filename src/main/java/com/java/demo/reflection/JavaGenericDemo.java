package com.java.demo.reflection;

import java.io.File;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGenericDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> scanBasePackageClass = JavaGenericDemo.class;
        String scanBasePackage = scanBasePackageClass.getPackage().getName();

        String classPath = getClassPath(scanBasePackageClass);
        File classPathDirectory = new File(classPath);
        File scanBasePackageDirectory = new File(classPathDirectory, scanBasePackage.replace(".","/"));

        File[] classFiles = scanBasePackageDirectory.listFiles(file -> {
            return file.isFile() && file.getName().endsWith(".class");
        });

        System.out.println("class path:"+classPath);
        System.out.println("scan base package:"+scanBasePackage);
        System.out.println("scan class files:"+ Arrays.stream(classFiles).map(File::getName).collect(Collectors.joining(",")));

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> targetClass = new ArrayList<>();
        for (File classFile:classFiles){
            String simpleName = classFile.getName().substring(0, classFile.getName().lastIndexOf("."));
            String className = scanBasePackage+"."+simpleName;
            Class<?> loadedClass = classLoader.loadClass(className);
            if (loadedClass.isAnnotationPresent(Repository.class)){
                targetClass.add(loadedClass);
            }
        }

        targetClass.stream()
                .filter(JavaGenericDemo::isConcrete)
                .filter(JavaGenericDemo::isCrudRepository)
                .forEach(type->{
                    Type[] types = type.getGenericInterfaces();
                    Arrays.stream(types)
                            .filter(t-> t instanceof ParameterizedType)
                            .map(t-> (ParameterizedType) t)
                            .filter(t-> CrudRepository.class.equals( t.getRawType()))
                            .forEach(parameterizedType->{
                                String typeName = parameterizedType.getActualTypeArguments()[0].getTypeName();

                                try {
                                    System.out.println(classLoader.loadClass(typeName));
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            });
                });
    }

    private static boolean isCrudRepository(Class<?> type){
        return CrudRepository.class.isAssignableFrom(type);
    }

    private static boolean isConcrete(Class<?> type){
        return !Modifier.isAbstract(type.getModifiers());
    }

    private static String getClassPath(Class<?> type){
        return type.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
    }

    interface CrudRepository<E extends Serializable>{

    }

    @Repository
    class UserRepository implements Comparable<User>,CrudRepository<User>,Serializable{
        @Override
        public int compareTo(User o) {
            return 0;
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Repository{
        String value() default "";
    }

    class User implements Serializable{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
