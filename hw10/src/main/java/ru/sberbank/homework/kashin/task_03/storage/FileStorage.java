package ru.sberbank.homework.kashin.task_03.storage;

import ru.sberbank.homework.kashin.task_03.proxy.MethodAndArgs;

import java.io.*;

import static java.util.Objects.nonNull;

public class FileStorage implements Storage {
    private String path;

    public FileStorage(String path) {
        this.path = path;
    }

    @Override
    public boolean containsKey(MethodAndArgs key) {
        return findFile(key.getFileName()) != null;
    }

    @Override
    public Object get(MethodAndArgs key) {
        return deserialize(key.getFileName());
    }

    @Override
    public Object put(MethodAndArgs key, Object value) {
        return serialize(key.getFileName(), value);
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

    private Object serialize(String fileName, Object object) {
        try (FileOutputStream fileOut = new FileOutputStream(getAbsolutPath(fileName));
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return object;
    }

    private Object deserialize(String fileName) {;
        try (FileInputStream fileIn = new FileInputStream(getAbsolutPath(fileName));
             ObjectInputStream in = new ObjectInputStream(fileIn)){
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private String getAbsolutPath(String fileName) {
        return path + "/" + fileName + ".ser";
    }
}
