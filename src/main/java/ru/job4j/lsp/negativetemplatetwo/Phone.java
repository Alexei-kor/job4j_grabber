package ru.job4j.lsp.negativetemplatetwo;

public abstract class Phone {

    private Owner owner;

    public Phone(Owner owner) {
        this.owner = owner;
    }

    public void call() {
        System.out.println("Звонок выполнен");
    }

}
