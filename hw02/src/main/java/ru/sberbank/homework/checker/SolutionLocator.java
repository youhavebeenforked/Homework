package ru.sberbank.homework.checker;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.homework.common.Calculator;

@Slf4j
public class SolutionLocator {

    public static void main(String[] args) {
        FastClasspathScanner scanner = new FastClasspathScanner();
        scanner.addClassLoader(SolutionLocator.class.getClassLoader());
        scanner.matchClassesImplementing(Calculator.class, subclass -> {
<<<<<<< HEAD
            String name = subclass.getPackage().getName();
            log.debug("Checking class from package: {}", name);
            SolutionChecker sc = new SolutionChecker(name);
            sc.startTesting(() -> get(subclass));
            log.info(sc.getStatus());
=======
            try {
                log.debug("Checking class from package: {}", subclass.getPackage().getName());
                sc.startTesting(subclass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("Calculator instance creation failed", e);
            }
>>>>>>> parent of e2fb9e7... Merge branch 'Dergun' into hw_03_01_terminal_interface
        });
        scanner.scan();
    }

    private static Calculator get(Class<? extends Calculator> subclass) {
        try {
            return subclass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Calculator instance creation failed", e);
            return null;
        }
    }
}
