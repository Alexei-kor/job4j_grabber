package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Emulator {

    public static void main(String[] args) {
        System.out.println("Укажите директорию для работы:");
        Scanner scanner = new Scanner(System.in);
        String dir = answer(scanner);
        checkFile(dir);
        DirFileCache dirFileCache = new DirFileCache(dir);
        int answer = 0;
        while (answer != 9) {
            System.out.println("Выберите вид операции:\n"
                    + "1 - загрузка содержимого всех текстовых файлов из указанной директории\n"
                    + "2 - загрузка содержимого указанного файла в кэш\n"
                    + "3 - чтение содержимого файла из кэша\n"
                    + "9 - выход");
            answer = Integer.parseInt(answer(scanner));
            if (answer == 1) {
                Stream.of(Paths.get(dir).toFile().listFiles())
                        .filter(e -> e.getName().endsWith(".txt"))
                        .map(e -> e.getName())
                        .forEach(dirFileCache::load);
            } else if (answer == 2) {
                System.out.println("Укажите имя файла:");
                String name = answer(scanner);
                checkFile(dir, name);
                dirFileCache.put(name, dirFileCache.load(name));
            } else if (answer == 3) {
                System.out.println("Укажите имя файла:");
                String name = answer(scanner);
                System.out.println(dirFileCache.get(name));
            }
        }
    }

    private static String answer(Scanner scanner) {
        return scanner.nextLine();
    }

    private static void checkFile(String dir) {
        if (!Paths.get(dir).toFile().exists()) {
            throw new IllegalArgumentException(String.format("No such file: %s", dir));
        }
    }

    private static void checkFile(String dir, String name) {
        if (!Paths.get(dir).toFile().exists()) {
            throw new IllegalArgumentException(String.format("No such file: %s in directory", name, dir));
        }
    }

}
