package ru.job4j.lsp.negativetemplatethree;

import ru.job4j.lsp.negativetemplateone.Human;

public class Humans {

    private String name;

    public Humans(String name) {
        this.name = name;
    }

    public void buyAlco() {
        System.out.println("Купил");
    }

    public static void main(String[] args) {
        Humans old = new OldHuman("Oleg");
        old.buyAlco();
        YoungHuman young = new YoungHuman("Masha");
        young.setAge(16);
        young.buyAlco();
    }

}
