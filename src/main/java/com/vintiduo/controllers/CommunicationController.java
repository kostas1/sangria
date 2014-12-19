package com.vintiduo.controllers;

import com.vintiduo.PageSessionHandler;
import com.vintiduo.WebSocketConfig;
import com.vintiduo.data.WebSocketRequest;
import com.vintiduo.test.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CommunicationController {

    @Autowired
    PageSessionHandler pageSessionHandler;

    @MessageMapping("/communication")
    public void communication(WebSocketRequest request, @Headers MessageHeaders headers,
                              @Header("simpSessionAttributes") Map<String, String> sessionAttributes, @Header("simpSessionId") String simpSessionId) {
        String sessionId = sessionAttributes.get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME);
        pageSessionHandler.handleWebSocketRequest(sessionId, simpSessionId, request, headers);
    }

    @RequestMapping("/{page}")
    @ResponseBody
    public String index(@PathVariable("page") String page, HttpSession session) {
        return pageSessionHandler.handleHttpRequest(page, session.getId());
    }
}
