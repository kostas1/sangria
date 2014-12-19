package com.vintiduo.page;

import org.springframework.messaging.MessageHeaders;

/**
 * Created by kostas on 2014.12.18.
 */
public interface Refreshable {

    void refresh(String sessionId, MessageHeaders headers, Component component);
}
