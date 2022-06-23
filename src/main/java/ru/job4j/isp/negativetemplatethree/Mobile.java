package ru.job4j.isp.negativetemplatethree;

public class Mobile implements Phone {

    @Override
    public void charge() {
        System.out.println("100%");
    }

    @Override
    public void call() {
        System.out.println("Звоню в Самару");
    }

    @Override
    public void foto() {
        System.out.println("Фотография получилась удачная");
    }

    @Override
    public void sendSMS() {
        System.out.println("СМС-ку отправил");
    }

}
