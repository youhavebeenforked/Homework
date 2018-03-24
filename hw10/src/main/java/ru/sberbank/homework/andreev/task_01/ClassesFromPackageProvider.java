package ru.sberbank.homework.andreev.task_01;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClassesFromPackageProvider {
    private String packagePath;
    private static final String ADDITIONAL_PATH = "out\\production\\classes";


    public ClassesFromPackageProvider(String packagePath) {
        this.packagePath = packagePath;
    }

    public List<Class> getClassesFromPackage() {
        File folder = new File(getPathToFolder());
        File[] listOfFiles = Optional.ofNullable(folder.listFiles()).orElse(new File[0]);
        return Arrays.stream(listOfFiles)
                .map(e -> createClass(e.getName()))
                .collect(Collectors.toList());
    }

    private Class createClass(String name) {
        Class result = null;
        try {
            result = Class.forName(packagePath + "." + name.replace(".class", ""));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getPathToFolder() {
        return System.getProperty("user.dir") + File.separator + ADDITIONAL_PATH + File.separator + packagePath.replace('.', File.separatorChar);
    }
}
