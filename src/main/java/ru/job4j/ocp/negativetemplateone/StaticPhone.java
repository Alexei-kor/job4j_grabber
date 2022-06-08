package ru.job4j.ocp.negativetemplateone;

public class StaticPhone extends Phone {
    @Override
    public void charge() {
        super.charge();
        System.out.println("Упс, зарядка не поддерживается");
    }
}
