package ru.job4j.isp.negativetemplateone;

public class AutomaticPencil implements Pencil {

    @Override
    public void sharpen() {
        System.out.println("Меня не надо точить");
    }
}
