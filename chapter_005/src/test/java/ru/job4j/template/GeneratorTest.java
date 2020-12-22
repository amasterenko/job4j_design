package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenAllParametersOk() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        Generator gen = new StringGenerator();
        assertThat(gen.produce(template, map), is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenOneMapKeyMissed() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        Generator gen = new StringGenerator();
        assertThat(gen.produce(template, map), is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenAllMapKeysMissed() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        String expected = "I am a Petr Arsentev, Who are you? ";
        Generator gen = new StringGenerator();
        assertThat(gen.produce(template, map), is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenOneTemplateParameterMissed() {
        String template = "I am a , Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        Generator gen = new StringGenerator();
        assertThat(gen.produce(template, map), is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenAllTemplateParametersMissed() {
        String template = "I am a , Who are $? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        Generator gen = new StringGenerator();
        assertThat(gen.produce(template, map), is(expected));
    }

}