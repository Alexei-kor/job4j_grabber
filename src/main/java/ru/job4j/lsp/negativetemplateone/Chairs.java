package ru.job4j.lsp.negativetemplateone;

public class Chairs implements Furniture<Human> {

    @Override
    public void sit(Human human) {
        System.out.println("Присядьте на стул");
    }
}
