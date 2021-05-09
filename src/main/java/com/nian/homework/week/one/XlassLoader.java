package com.nian.homework.week.one;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class XlassLoader extends ClassLoader {


    @Override
    public Class<?> findClass(String name) {
        InputStream is = null;
        byte[] bytes;
        try {
            //读文件
            File file = new File(Objects.requireNonNull(XlassLoader.class.getClassLoader().getResource("Hello.xlass")).getPath());
            is = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            is.read(bytes);
            //还原字节数组
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> hello = new XlassLoader().findClass("Hello");
        if (hello != null) {
            Method method = hello.getMethod("hello");
            method.invoke(hello.newInstance());
        }
    }

}
