package ru.job4j.lsp.negativetemplateone;

public class GlassChair extends Chairs {

    @Override
    public void sit(Human human) {
        throw new IllegalArgumentException("Нееет, нельзя на него садиться!");
    }
}
