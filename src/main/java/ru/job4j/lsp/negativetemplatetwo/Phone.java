package ru.job4j.lsp.negativetemplatetwo;

public abstract class Phone {

    private Owner owner;

    public Phone(Owner owner) {
        this.owner = owner;
    }

    public void foto() {
        System.out.println("Сфотографировал");
    }

}
