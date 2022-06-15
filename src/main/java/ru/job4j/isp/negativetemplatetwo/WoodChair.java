package ru.job4j.isp.negativetemplatetwo;

public class WoodChair implements Chairs {

    @Override
    public void changeHeigth(int height) {
        System.out.println("Я не умею изменять высоту");
    }
}
