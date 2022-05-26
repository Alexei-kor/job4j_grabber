package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
public class GeneratorTest {

    @Test
    public void whenOK() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Ivanov", "subject", "you");
        assertThat(new GeneratorOne().produce(template, map), is("I am a Ivanov, Who are you?"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenMapIsNotKey() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name1", "Ivanov", "subject", "you");
        new GeneratorOne().produce(template, map);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenMapIsMoreKeys() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Ivanov", "subject", "you", "city", "Rostov");
        new GeneratorOne().produce(template, map);
    }

}