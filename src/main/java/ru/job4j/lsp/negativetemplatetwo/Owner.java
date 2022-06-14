package ru.job4j.lsp.negativetemplatetwo;

public class Owner {

    private String name;

    public Owner(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Owner owner = new Owner("Oleg");
        Phone phone = new StaticPhone(owner);
        if (phone.getClass() != StaticPhone.class) {
            phone.foto();
        }
    }
}
