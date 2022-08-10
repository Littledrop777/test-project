package by.exlab.shire_test.controller;

import by.exlab.shire_test.entity.Chat;
import by.exlab.shire_test.service.AppUserService;
import by.exlab.shire_test.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;
  private final AppUserService userService;

  @MessageMapping("/message")
  @SendTo("/chat/messages")
  public Chat chat(Chat chat) {
    System.out.println(chat);
    return chat;
  }
}
