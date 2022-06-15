package ru.job4j.isp.negativetemplatethree;

public class Mobile implements Phone {

    @Override
    public void charge() {
        System.out.println("100%");
    }
}
