package com.vintiduo;

import com.vintiduo.data.WebSocketRequest;
import com.vintiduo.data.WebSocketResponse;
import com.vintiduo.page.Component;
import com.vintiduo.page.Page;
import com.vintiduo.page.Refreshable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kostas on 2014.12.18.
 */
@org.springframework.stereotype.Component
public class PageSessionHandler implements ApplicationContextAware, Refreshable {

    @Autowired
    SimpMessagingTemplate brokerMessagingTemplate;

    ApplicationContext context;
    Map<String, Map<String, Page>> sessions = new HashMap<>();

    public void handleRequest(String sessionId, WebSocketRequest request, MessageHeaders headers) {
        Map<String, Page> pages = sessions.getOrDefault(sessionId, new HashMap<String, Page>());
        Page page = pages.getOrDefault(request.getPage(), context.getBean(request.getPage(), Page.class));
        page.setSessionId(sessionId);
        page.setHeaders(headers);
        page.setRefreshHandler(this);
        page.handleEvent(request.getEvent());

        pages.put(request.getPage(), page);
        sessions.put(sessionId, pages);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void refresh(String sessionId, MessageHeaders headers, Component component) {
        WebSocketResponse response = new WebSocketResponse();
        response.setId(component.getId());
        response.setData(component.toString());
        brokerMessagingTemplate.convertAndSendToUser(sessionId, "/topic/communication", response, headers);
    }
}
