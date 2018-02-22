package ru.sberbank.jschool.homework.bobrov.caesar;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 22.02.2018
 */


public class EncryptedClassLoader extends ClassLoader {
    private String pathToClass;

    public EncryptedClassLoader(ClassLoader parent, String pathToClass) {
        super(parent);
        this.pathToClass = pathToClass;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte b[] = fetchClassFromFS(pathToClass + name + ".class");
            return defineClass(name, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(name);
        } catch (IOException ex) {
            return super.findClass(name);
        }

    }

    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));
        long length = new File(path).length();

        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;


    }

}
