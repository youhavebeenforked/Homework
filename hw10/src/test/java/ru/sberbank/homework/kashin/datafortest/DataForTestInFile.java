package ru.sberbank.homework.kashin.datafortest;

import java.io.File;
import java.util.Arrays;

public class DataForTestInFile {
    public static final String ROOT = "storage";

    public static void clearRootDirectory() {
        File rootPath = new File(ROOT);
        File[] files = rootPath.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(file -> file.delete());
        }
    }
}
