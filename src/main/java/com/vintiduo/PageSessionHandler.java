package com.vintiduo;

import com.vintiduo.data.WebSocketRequest;
import com.vintiduo.data.WebSocketResponse;
import com.vintiduo.page.FrameworkContext;
import com.vintiduo.page.TemplateContextBuilder;
import com.vintiduo.page.components.Element;
import com.vintiduo.page.components.Page;
import com.vintiduo.page.Refreshable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kostas on 2014.12.18.
 */
@org.springframework.stereotype.Component
public class PageSessionHandler implements ApplicationContextAware, Refreshable {

    @Autowired
    TemplateContextBuilder templateContextBuilder;

    @Autowired
    SimpMessagingTemplate brokerMessagingTemplate;

    ApplicationContext context;
    Map<String, Map<String, Page>> sessions = new HashMap<>();

    public void handleWebSocketRequest(String sessionId, String simpSessionId, WebSocketRequest request, MessageHeaders headers) {
        System.out.println(request);
        Page page = getPageForHttpSessionIdAndName(sessionId, request.getPage());
        if (!page.getFrameworkContext().isComplete()) {
            page.setFrameworkContext(new FrameworkContext(simpSessionId, sessionId, headers, this, templateContextBuilder));
        }
        page.handleEvent(request.getEvent());
    }

    private Page getPageForHttpSessionIdAndName(String sessionId, String name) {
        Map<String, Page> pages = sessions.getOrDefault(sessionId, new HashMap<String, Page>());
        Page page = pages.getOrDefault(name, context.getBean(name, Page.class));
        if (page.getFrameworkContext() == null) {
            page.setFrameworkContext(new FrameworkContext(null, sessionId, null, this, templateContextBuilder));
        }
        pages.put(name, page);
        sessions.put(sessionId, pages);
        return page;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void refresh(String sessionId, MessageHeaders headers, Element element) {
        WebSocketResponse response = new WebSocketResponse();
        response.setId(element.getId());
        response.setData(element.toString());
        response.setProcessed(new Date());
        brokerMessagingTemplate.convertAndSendToUser(sessionId, "/topic/communication", response, headers);
    }

    public String handleHttpRequest(String page, String sessionId) {
        return getPageForHttpSessionIdAndName(sessionId, page).toString();
    }
}
