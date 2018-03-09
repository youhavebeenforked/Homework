package ru.sberbank.homework.kashin.task_03.storage;

import java.io.*;

import static java.util.Objects.nonNull;

public class FileStorage implements Storage {
    private String path;

    public FileStorage(String path) {
        this.path = path;
    }

    @Override
    public boolean containsKey(String  key) {
        return findFile(key) != null;
    }

    @Override
    public Object get(String  key) {
        return deserialize(key);
    }

    @Override
    public Object put(String key, Object value) {
        return serialize(key, value);
    }

    private File findFile(String fileName) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        File result = null;
        if (nonNull(files)) {
            for (File file : files) {
                if (fileName.equals(file.getName())) {
                    result = file;
                }
            }
        }
        return result;
    }

    private Object serialize(String fileName, Object object){
        String filePath = path + "/" + fileName + ".ser";
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(object);
        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
            e.printStackTrace();
        }
        return object;
    }

    private Object deserialize(String fileName){
        String filePath = path + "/" + fileName + ".ser";
        Object result;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            result = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
