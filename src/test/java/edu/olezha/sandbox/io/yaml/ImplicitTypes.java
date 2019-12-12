package edu.olezha.sandbox.io.yaml;

import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ImplicitTypes {

    @Test
    public void whenLoadYAML_thenLoadCorrectImplicitTypes() throws ParseException {
        Map<Object, Object> document = new HashMap<>();
        document.put(Double.valueOf("1"), Double.valueOf("1"));
        document.put("1.0", "1.");
        document.put(2, 2);
        document.put("2", "2");
        document.put("3L", 3L);
        document.put(new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-11"), "myDate");
        document.put("myMap", new HashMap<Object, Object>() {{
            put("1", "1");
            put("2", "2");
        }});
        Map<?, ?> map = null;
        document.put(null, map);
        System.out.println(document + System.lineSeparator());

        Yaml yaml = new Yaml(new Constructor(), new Representer(), new DumperOptions(), new Resolver() {
            @Override
            protected void addImplicitResolvers() {}
        });

        StringWriter writer = new StringWriter();
        yaml.dump(document, writer);
        String dump = writer.toString();
        System.out.println(dump);

        document = yaml.load(dump);
        assertNotNull(document);
        print(document);

        document = yaml.load(".0: .1\n1.0: 1.");
        assertNotNull(document);
        print(document);
    }

    private void print(Map<?, ?> map) {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + (entry.getKey() == null ? null : entry.getKey().getClass()) + " : " +
                    entry.getValue() + " " + (entry.getValue() == null ? null : entry.getValue().getClass()));
        }
        System.out.println();
    }
}
