package by.exlab.shire_test.controller;

import by.exlab.shire_test.dto.MessageCreateDto;
import by.exlab.shire_test.dto.MessageReadDto;
import by.exlab.shire_test.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

//  private SimpMessagingTemplate simpMessagingTemplate;
  private final MessageService messageService;

  @MessageMapping("/chat/{id}")
  @SendTo("/topic/{id}")
  public MessageReadDto sendMessage(@DestinationVariable Long id, MessageCreateDto messageCreateDto) {
    MessageReadDto messageReadDto = messageService.create(messageCreateDto);
//    simpMessagingTemplate.convertAndSend("chat/messages/" + message.getChatId(), messageCreateDto);
    return messageReadDto;
  }

  @GetMapping("/messages/{chatId}")
  public List<MessageReadDto> getAllMessages(@PathVariable Long chatId) {
    List<MessageReadDto> messages = messageService.findAllByChatId(chatId);
    return messages;
  }

}
