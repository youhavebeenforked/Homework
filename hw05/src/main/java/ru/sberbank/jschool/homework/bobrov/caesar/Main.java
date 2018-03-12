package ru.sberbank.jschool.homework.bobrov.caesar;

public class Main {
    public static void main(String[] args) {
        EncryptedClassLoader encr = new EncryptedClassLoader(null, "/home/malamut/projects/Homework/encrClass/MainCaesar.class", 3);
        try {
            encr.findClass("/home/malamut/projects/Homework/encrClass/MainCaesar.class");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
