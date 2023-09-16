package com.chatApp.chitchat.controller;

import lombok.*;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMassge {
    private String message;
    private String sender;
    private MessageType type;
}
