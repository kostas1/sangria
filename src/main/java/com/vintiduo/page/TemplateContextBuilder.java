package com.vintiduo.page;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import sun.java2d.pipe.RenderBuffer;

import java.io.StringWriter;
import java.util.*;

/**
 * Created by kostas on 2014.12.18.
 */
public class TemplateContextBuilder {

    private String name;

    private Map<String, Set<Object>> objectMap = new HashMap<>();
    private Map<String, Set<String>> stringMap = new HashMap<>();

    public TemplateContextBuilder(String name) {
        this.name = name;
    }

    public TemplateContextBuilder put(String key, Object value) {
        Set<Object> list = objectMap.getOrDefault(key, new HashSet<>());
        list.add(value);
        objectMap.put(key, list);
        return this;
    }

    public TemplateContextBuilder put(String key, String value) {
        Set<String> list = stringMap.getOrDefault(key, new HashSet<String>());
        list.add(value);
        stringMap.put(key, list);
        return this;
    }

    public String render() {
        Template template = Velocity.getTemplate(String.format("./src/main/resources/templates/%s.vm", name));
        VelocityContext context = new VelocityContext();
        for (Map.Entry<String, Set<Object>> entry: objectMap.entrySet()) {
            Object obj = entry.getValue();
            if (entry.getValue().size() == 1)
                obj = entry.getValue().iterator().next();
            context.put(entry.getKey(), obj);
        }
        for (Map.Entry<String, Set<String>> entry: stringMap.entrySet()) {
            context.put(entry.getKey(), StringUtils.join(entry.getValue(), " "));
        }

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    public TemplateContextBuilder remove(String key) {
        stringMap.remove(key);
        objectMap.remove(key);
        return this;
    }
}
