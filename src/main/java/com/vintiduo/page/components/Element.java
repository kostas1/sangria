package com.vintiduo.page.components;

import com.vintiduo.data.Event;
import com.vintiduo.page.ComponentData;
import com.vintiduo.page.FrameworkContext;
import com.vintiduo.page.events.ClickListener;
import com.vintiduo.page.events.Listener;
import org.apache.commons.lang.RandomStringUtils;

import java.util.*;

public abstract class Element {

    private String                                  id = RandomStringUtils.randomAlphanumeric(16);
    private Set<Element>                            elements = new HashSet<>();
    private Map<String, List<Listener>>             listeners = new HashMap<>();
    private FrameworkContext                        frameworkContext;
    private ComponentData                           componentData = new ComponentData();

    public String getTemplateName() {
        return getClass().getSimpleName().toLowerCase();
    }

    protected void registerListener(Listener listener) {
        List<Listener> listenerList = listeners.getOrDefault(listener.name(), new ArrayList<Listener>());
        listenerList.add(listener);
        listeners.put(listener.name(), listenerList);
    }

    public void handleEvent(Event event) {
        System.out.println(event);
        Element source = getAllElements().get(event.getSource());
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

    private Map<String, Element> getAllElements() {
        Map<String, Element> componentSet = new HashMap<>();
        for (Element element : elements) {
            componentSet.put(element.getId(), element);
            componentSet.putAll(element.getAllElements());
        }
        return componentSet;
    }

    public ComponentData getData() {
        return componentData.put("id", id).add("class", "component").add("class", getTemplateName());
    }

    public String getId() {
        return id;
    }

    public void setFrameworkContext(FrameworkContext frameworkContext) {
        this.frameworkContext = frameworkContext;
        for (Element element : getAllElements().values()) {
            element.setFrameworkContext(frameworkContext);
        }
    }

    protected void refreshElement() {
        if (frameworkContext != null) {
            frameworkContext.refresh(this);
        } else {
            // todo log error
        }
    }

    @Override
    public String toString() {
        return frameworkContext.render(this);
    }

    protected void addComponent(Element element) {
        element.setFrameworkContext(frameworkContext);
        elements.add(element);
    }

    public Set<Element> getDirectChildElements() {
        return elements;
    }

    public void addClickListener(ClickListener listener) {
        registerListener(listener);
    }

    public FrameworkContext getFrameworkContext() {
        return frameworkContext;
    }
}