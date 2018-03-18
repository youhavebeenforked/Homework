package ru.sberbank.homework.dergun.task_03.Storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final static int BUFFER = 2048;

    public static void compress(String directory, String fileName) {
        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fos = new FileOutputStream(directory + fileName + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(fileName + ".txt");
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(directory + fileName + ".txt");
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            in.close();
            zos.closeEntry();
            zos.close();
            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
