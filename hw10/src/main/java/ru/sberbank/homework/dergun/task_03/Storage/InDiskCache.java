package ru.sberbank.homework.dergun.task_03.Storage;

import ru.sberbank.homework.dergun.task_03.CacheKey;
import ru.sberbank.homework.dergun.task_03.CacheSettings;

import java.io.*;

public class InDiskCache implements CacheStorage {
    private CacheSettings settings = new CacheSettings();
    private final String monitor = "";

    @Override
    public void addObject(CacheKey key, Object object) {
        synchronized (monitor) {
//            if (!(object.getClass() instanceof Serializable)) {
//                System.out.println("This Service is not Serializable");
//                return;
//            }
            try (FileOutputStream fileOut = new FileOutputStream(settings.getDirectory() + "\\" + key.getFileName() + ".txt");
                 ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
                objOut.writeObject(object);
                if (settings.isZip()) {
                    Zip.compress(settings.getDirectory() + "\\", key.getFileName());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Error: Can't create file");
            }
        }
    }

    @Override
    public boolean contains(CacheKey key) {
        File directory = new File(settings.getDirectory());
        String [] files = directory.list();
        String fileName = key.getFileName();
        for (String file : files) {
            if (file.startsWith(fileName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changeSetting(CacheSettings settings) {
        this.settings = settings;
    }

    @Override
    public Object getByKey(CacheKey key) {
        synchronized (monitor) {
            try (FileInputStream fileIn = new FileInputStream(settings.getDirectory() + "\\" + key.getFileName() + ".txt");
                 ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
                return objIn.readObject();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Error: Can't load file");
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found");
            }
            return null;
        }
    }
}
