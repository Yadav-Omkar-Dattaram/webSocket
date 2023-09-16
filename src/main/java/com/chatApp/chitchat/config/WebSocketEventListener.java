package com.chatApp.chitchat.config;

import com.chatApp.chitchat.controller.ChatMassge;
import com.chatApp.chitchat.controller.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j

public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplates;

    @EventListener
    public void handleSocketDissConnect(SessionDisconnectEvent event){
        //Imlement later
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            log.info("user disconnected : {}" , username);
            var chatMessage = ChatMassge.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();


            messageTemplates.convertAndSend("/topic/public", chatMessage);
        }
    }
}
