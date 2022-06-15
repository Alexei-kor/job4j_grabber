package ru.job4j.isp.negativetemplatethree;

public class Static implements Phone {

    @Override
    public void charge() {
        System.out.println("oops");
    }
}
