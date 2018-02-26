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

    public EncryptedClassLoader(String pathToClass, int offset) {
        this.pathToClass = pathToClass;
        this.offset = offset;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String cname = String.format("%s.%s", pathToClass, name);
        byte[] classBytes = null;
        try {
            classBytes = loadClassBytes(cname);
        } catch (IOException e) {
            throw new ClassNotFoundException(cname);
        }
        Class<?> cl = defineClass(cname, classBytes, 0, classBytes.length);
        if (cl == null) {
            throw new ClassNotFoundException(cname);
        }
        return cl;

    }

    private byte[] loadClassBytes(String name) throws FileNotFoundException, IOException {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name.replace(".", "/") + ".caesar");
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()
        ) {
            int ch;
            while ((ch = inputStream.read()) != -1) {
                byte b = (byte) (ch - offset);
                buffer.write(b);
            }
            return buffer.toByteArray();
        }

    }

}
