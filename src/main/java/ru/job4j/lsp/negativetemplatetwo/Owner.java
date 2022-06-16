package ru.job4j.lsp.negativetemplatetwo;

public class Owner {

    private String name;

    public Owner(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Owner owner = new Owner("Oleg");
        Phone phoneS = new StaticPhone(owner);
        phoneS.call();
        MobilePhone phoneM = new MobilePhone(owner);
        phoneM.setBatteryLevel(4);
        phoneM.call();
    }
}
