package by.exlab.shire_test.controller;

import by.exlab.shire_test.entity.ChatMessage;
import by.exlab.shire_test.service.AppUserService;
import by.exlab.shire_test.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

  private SimpMessagingTemplate simpMessagingTemplate;
  private final ChatService chatService;
  private final AppUserService userService;

  @MessageMapping("/message")
  @SendTo("/chat/messages")
  public ChatMessage sendMessage(ChatMessage chatMessage) {
    System.out.println(chatMessage);
//    simpMessagingTemplate.convertAndSend("chat/messages/" + chatMessage.getUserChat(), chatMessage);
    return chatMessage;
  }
  @GetMapping("/messages/")
//  @SendTo("/chat/messages")
  public ChatMessage getAllMessages(@RequestParam Long userChatId, ChatMessage chatMessage) {
    List<ChatMessage> messages = chatService.findAllByUserChatId(userChatId);
    return chatMessage;
  }

}
