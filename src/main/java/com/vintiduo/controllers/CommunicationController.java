package com.vintiduo.controllers;

import com.vintiduo.PageSessionHandler;
import com.vintiduo.data.WebSocketRequest;
import com.vintiduo.data.WebSocketResponse;
import com.vintiduo.page.Page;
import com.vintiduo.test.HomePage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import java.security.Principal;
import java.util.Map;

/**
 * Created by kostas on 2014.12.17.
 */
@Controller
public class CommunicationController implements ApplicationContextAware {

    ApplicationContext context;

    @Autowired
    PageSessionHandler pageSessionHandler;

    @MessageMapping("/communication")
    public void communication(Message<Object> message, @Header("simpSessionId") String sessionId, WebSocketRequest request) {
        pageSessionHandler.handleRequest(sessionId, request, message.getHeaders());
    }

    private HomePage page = new HomePage();

    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return page.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
