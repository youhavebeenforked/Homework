package ru.sberbank.homework.dergun.task_03;

import java.nio.file.Paths;

public class CacheSettings {
    private String directory;
    private String fileName;
    private boolean typeFile;
    private boolean zip;
    private int maxSizeList;

    public CacheSettings() {
        defaultSettings();
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String root) {
        this.directory = root;
    }

    public boolean isTypeFile() {
        return typeFile;
    }

    public void setTypeFile(boolean typeFile) {
        this.typeFile = typeFile;
    }

    public boolean isZip() {
        return zip;
    }

    public void setZip(boolean zip) {
        this.zip = zip;
    }

    public void defaultSettings() {
        directory = Paths.get("src", "main", "resources").toAbsolutePath().toString();
        typeFile = false;
        zip = false;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getMaxSizeList() {
        return maxSizeList;
    }

    public void setMaxSizeList(int maxSizeList) {
        this.maxSizeList = maxSizeList;
    }
}
