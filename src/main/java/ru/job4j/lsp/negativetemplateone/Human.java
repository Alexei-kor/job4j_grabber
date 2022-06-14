package ru.job4j.lsp.negativetemplateone;

public class Human {

    private String name;

    public Human(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Human oleg = new Human("Oleg");
        Human masha = new Human("Masha");
        Chairs chairs1 = new WoodChair();
        chairs1.sit(oleg);
        Chairs chairs2 = new GlassChair();
        chairs2.sit(masha);

    }

}
