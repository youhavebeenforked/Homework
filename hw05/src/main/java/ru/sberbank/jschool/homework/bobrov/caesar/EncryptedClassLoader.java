package ru.sberbank.jschool.homework.bobrov.caesar;


import java.io.*;


/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 22.02.2018
 */


public class EncryptedClassLoader extends ClassLoader {
    private String pathToClass;
    private int offset;

    public EncryptedClassLoader(ClassLoader parent, String pathToClass, int offset) {
        super(parent);
        this.pathToClass = pathToClass;
        this.offset = offset;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = null;
        try {
            classBytes = loadClassBytes(name);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }

        Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
        if (cl == null) throw new ClassNotFoundException(name);
        return cl;

    }

    private byte[] loadClassBytes(String path) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(path);
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch;
            while ((ch = in.read()) != -1) {
                byte b = (byte) (ch - offset);
                buffer.write(b);
            }
            return buffer.toByteArray();
        } finally {
            in.close();
        }

    }

}
