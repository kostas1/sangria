package com.vintiduo.controllers;

import com.vintiduo.PageSessionHandler;
import com.vintiduo.WebSocketConfig;
import com.vintiduo.data.WebSocketRequest;
import com.vintiduo.page.log.Logger;
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

    Logger logger = Logger.forClass(CommunicationController.class);

    @Autowired
    PageSessionHandler pageSessionHandler;

    @MessageMapping("/communication")
    public void communication(WebSocketRequest request, @Headers MessageHeaders headers,
                              @Header("simpSessionAttributes") Map<String, String> sessionAttributes, @Header("simpSessionId") String webSocketSessionId) {
        String httpSessionId = sessionAttributes.get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME);

        logger.info("communication", "received request", "request", request, "webSocketSessionId", webSocketSessionId, "httpSessionId", httpSessionId);
        pageSessionHandler.handleWebSocketRequest(httpSessionId, webSocketSessionId, request, headers);
    }

    @RequestMapping("/{page}")
    @ResponseBody
    public String index(@PathVariable("page") String page, HttpSession session) {
        logger.info("index", "received request", "page", page, "session", session);
        return pageSessionHandler.handleHttpRequest(page, session.getId());
    }
}
