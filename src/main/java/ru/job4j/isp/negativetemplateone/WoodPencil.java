package ru.job4j.isp.negativetemplateone;

public class WoodPencil implements Pencil {

    @Override
    public void sharpen() {
        System.out.println("Поточил карандаш точилкой");
    }
}
