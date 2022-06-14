package ru.job4j.lsp.negativetemplatethree;

import ru.job4j.lsp.negativetemplateone.Human;

public class Humans {

    private String name;

    public Humans(String name) {
        this.name = name;
    }

    public boolean crossRoadOnRed() {
        return false;
    }

    public static void main(String[] args) {
        Humans old = new OldHuman("Oleg");
        System.out.println(old.crossRoadOnRed());
        Humans young = new YoungHuman("Masha");
        System.out.println(young.crossRoadOnRed());
    }

}
