package ru.job4j.lsp.negativetemplatetwo;

public class MobilePhone extends Phone {

    int batteryLevel;

    public MobilePhone(Owner owner) {
        super(owner);
        batteryLevel = 100;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void call() {
        if (batteryLevel < 5) {
            System.out.println("Звонок невозможен, низкий заряд аккумулятора");
        }
    }
}
