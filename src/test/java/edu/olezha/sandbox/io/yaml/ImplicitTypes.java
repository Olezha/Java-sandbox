package edu.olezha.sandbox.io.yaml;

import lombok.Data;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

import java.util.Map;

import static org.junit.Assert.*;

@Data
public class ImplicitTypes {

    @Test
    public void whenLoadYAML_thenLoadCorrectImplicitTypes() {
        Yaml yaml = new Yaml(new Constructor(), new Representer(), new DumperOptions(), new Resolver() {
            @Override
            protected void addImplicitResolvers() {}
        });
        Map<Object, Object> document = yaml.load(
                "1.0: 1.000\n" +
                        "2: 002\n" +
                        ((long) Integer.MAX_VALUE + 1) + ": " + ((long) Integer.MAX_VALUE + 1) + "s\n" +
                        "2018-07-22: 2018/07/22\n");

        assertNotNull(document);
        assertEquals(4, document.size());

        System.out.println(document);

        for (Map.Entry entry : document.entrySet()) {
            System.out.println(entry.getKey().getClass() + " " + entry.getValue().getClass());
        }
    }
}
