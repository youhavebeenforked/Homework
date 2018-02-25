package ru.sberbank.jschool.homework.bobrov.caesar;

import java.io.*;

public class CreateCryptoClass {

    void createCaesar(String in, String out, int offset) {
        try (FileInputStream input = new FileInputStream(in);
             FileOutputStream output = new FileOutputStream(out);
        ) {
            int ch;
            while ((ch = input.read()) != -1) {
                output.write((char) (ch + offset));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
