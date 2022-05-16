package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) {
        System.out.println("Укажите директорию для работы: ");
        Scanner scanner = new Scanner(System.in);
        DirFileCache dirFileCache = new DirFileCache(scanner.nextLine());
        int answer = 0;
        while (answer != 9) {
            System.out.println("Выберите вид операции:\n"
                    + "1 - загрузка содержимого всех текстовых файлов из указанной директории\n"
                    + "2 - загрузка содержимого указанного файла в кэш\n"
                    + "3 - чтение содержимого файла из кэша\n"
                    + "9 - выход");
            answer = Integer.parseInt(scanner.nextLine());
            if (answer == 1) {
                dirFileCache.getTextFiles().forEach(dirFileCache::load);
            } else if (answer == 2) {
                System.out.println("Укажите имя файла: ");
                String name = scanner.nextLine();
                dirFileCache.put(name, dirFileCache.load(name));
            } else if (answer == 3) {
                System.out.println("Укажите имя файла: ");
                String name = scanner.nextLine();
                System.out.println(dirFileCache.get(name));
            }
        }


    }

}
