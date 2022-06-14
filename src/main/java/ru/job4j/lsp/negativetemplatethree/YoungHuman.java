package ru.job4j.lsp.negativetemplatethree;

import java.util.List;

public class YoungHuman extends Humans {

    public YoungHuman(String name) {
        super(name);
    }

    @Override
    public boolean crossRoadOnRed() {
        return true;
    }
}
