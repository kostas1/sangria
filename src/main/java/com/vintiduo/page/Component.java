package com.vintiduo.page;

import com.vintiduo.PageSessionHandler;
import com.vintiduo.data.Event;
import com.vintiduo.data.WebSocketRequest;
import com.vintiduo.page.events.Listener;
import org.springframework.messaging.MessageHeaders;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by kostas on 2014.12.18.
 */
public abstract class Component {

    private String id;
    private String sessionId;
    private TemplateContextBuilder builder;
    private Set<Component> components;
    private Map<String, List<Listener>> listeners = new HashMap<>();
    private PageSessionHandler refreshHandler;
    private MessageHeaders headers;

    public Component() {
        this(null);
    }

    public Component(String id) {
        if (id == null)
            id = "generate id here";
        this.id = id;
        this.builder = new TemplateContextBuilder(getTemplateName());
        this.components = new HashSet<>();
    }

    protected String getTemplateName() {
        return getClass().getSimpleName().toLowerCase();
    }


    protected void registerListener(Listener listener) {
        List<Listener> listenerList = listeners.getOrDefault(listener.name(), new ArrayList<Listener>());
        listenerList.add(listener);
        listeners.put(listener.name(), listenerList);
    }

    public void handleEvent(Event event) {
        Component source = getAllComponents().get(event.getSource());
        if (source != null) {
            List<Listener> listenerList = source.getListeners().get(event.getType());
            if (listenerList != null) {
                for (Listener listener: listenerList) {
                    listener.handle(event);
                }
            }
        }
    }

    protected Map<String, List<Listener>> getListeners() {
        return listeners;
    }

    private Map<String, Component> getAllComponents() {
        Map<String, Component> componentSet = new HashMap<>();
        for (Component component: components) {
            componentSet.put(component.getId(), component);
            componentSet.putAll(component.getAllComponents());
        }
        return componentSet;
    }

    protected TemplateContextBuilder getBuilder() {
        return this.builder.put("id", id).put("class", "component");
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return getBuilder().render();
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        for (Component component: getAllComponents().values()) {
            component.setSessionId(sessionId);
        }
    }

    protected void refreshComponent() {
        refreshHandler.refresh(sessionId, headers, this);
    }

    public void addComponent(Component component) {
        component.setSessionId(getSessionId());
        component.setRefreshHandler(refreshHandler);
        component.setHeaders(headers);
        components.add(component);
    }

    public Set<Component> getComponents() {
        return components;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setRefreshHandler(PageSessionHandler refreshHandler) {
        this.refreshHandler = refreshHandler;
        for (Component component: getAllComponents().values()) {
            component.setRefreshHandler(refreshHandler);
        }
    }

    public void setHeaders(MessageHeaders headers) {
        this.headers = headers;
        for (Component component: getAllComponents().values()) {
            component.setHeaders(headers);
        }
    }

    public MessageHeaders getHeaders() {
        return headers;
    }
}