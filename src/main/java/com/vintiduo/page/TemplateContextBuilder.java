package com.vintiduo.page;

import com.vintiduo.page.components.Element;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.*;

@Component
@Scope("singleton")
public class TemplateContextBuilder {

    @Value("${template.theme}")
    private String theme;

    public String render(Element element) {
        Template template = Velocity.getTemplate(String.format("./src/main/resources/templates/%s/%s.vm", theme, element.getTemplateName()));
        VelocityContext context = new VelocityContext();
        for (Map.Entry<String, Set<Object>> entry: element.getData().entrySet()) {
            if (entry.getValue().size() == 1) {
                context.put(entry.getKey(), entry.getValue().iterator().next());
            } else {
                context.put(entry.getKey(), StringUtils.join(entry.getValue(), " "));
            }
        }
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }
}
