package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String text = get(key);
        if (text == null) {
            text = readFile(key);
            put(key, text);
        }
        return text;
    }

    @Override
    public void put(String key, String value) {
        super.put(key, value);
    }

    @Override
    public String get(String key) {
        return super.get(key);
    }

    public List<String> getTextFiles() {
        Path path = Paths.get(cachingDir);
        return List.of(path.normalize().toFile().listFiles())
                .stream()
                .filter(e -> e.getName().endsWith(".txt"))
                .map(e -> e.getName())
                .collect(Collectors.toList());
    }

    private String readFile(String name) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(
                new FileReader(String.format("%s/%s", cachingDir, name))
            )) {
            br.lines().forEach(stringBuilder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
