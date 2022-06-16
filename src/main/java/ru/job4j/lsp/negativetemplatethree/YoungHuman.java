package ru.job4j.lsp.negativetemplatethree;

import java.util.List;

public class YoungHuman extends Humans {

    int age;

    public YoungHuman(String name) {
        super(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void buyAlco() {
        if (age < 18) {
            System.out.println("Вам не продаем, нужно подрасти");
        }
    }
}
