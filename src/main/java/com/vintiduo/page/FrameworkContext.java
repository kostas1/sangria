package com.vintiduo.page;

import com.vintiduo.page.components.Element;
import org.springframework.messaging.MessageHeaders;

/**
 * Created by kostas on 2014.12.19.
 */
public class FrameworkContext {
    private String simpSessionId;
    private String httpSessionId;
    private Refreshable refreshHandler;
    private MessageHeaders headers;
    private TemplateContextBuilder templateContextBuilder;

    public FrameworkContext(String simpSessionId, String sessionId, MessageHeaders headers, Refreshable refreshHandler, TemplateContextBuilder templateContextBuilder) {
        this.simpSessionId = simpSessionId;
        this.httpSessionId = sessionId;
        this.refreshHandler = refreshHandler;
        this.headers = headers;
        this.templateContextBuilder = templateContextBuilder;
    }

    public void refresh(Element element) {
        if (refreshHandler != null) {
            refreshHandler.refresh(simpSessionId, headers, element);
        }
    }

    public String render(Element element) {
        return templateContextBuilder.render(element);
    }

    public boolean isComplete() {
        return     simpSessionId != null
                && httpSessionId != null
                && refreshHandler != null
                && headers != null
                && templateContextBuilder != null;
    }
}
