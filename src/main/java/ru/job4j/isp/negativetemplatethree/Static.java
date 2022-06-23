package ru.job4j.isp.negativetemplatethree;

public class Static implements Phone {

    @Override
    public void charge() {
        System.out.println("oops");
    }

    @Override
    public void call() {
        System.out.println("Звоню в Москву");
    }

    @Override
    public void foto() {
        System.out.println("Фотографировать не умею");
    }

    @Override
    public void sendSMS() {
        System.out.println("Отправлять СМС не умею");
    }
}
