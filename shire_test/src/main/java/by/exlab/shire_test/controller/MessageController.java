package by.exlab.shire_test.controller;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import by.exlab.shire_test.dto.ChatCreateDto;
import by.exlab.shire_test.dto.ChatReadDto;
import by.exlab.shire_test.dto.MessageCreateDto;
import by.exlab.shire_test.dto.MessageReadDto;
import by.exlab.shire_test.service.ChatService;
import by.exlab.shire_test.service.MessageService;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

  //  private SimpMessagingTemplate simpMessagingTemplate;
  private final MessageService messageService;
  private final ChatService chatService;

  @MessageMapping("/chats/{id}")
  @SendTo("/topic/{id}")
  public MessageReadDto sendMessage(@DestinationVariable Long id,
      MessageCreateDto messageCreateDto) {
    MessageReadDto messageReadDto = messageService.create(
        messageCreateDto);   //set chatId or get in messageCreateDto
//    simpMessagingTemplate.convertAndSend("chat/messages/" + message.getChatId(), messageCreateDto);
    return messageReadDto;
  }

  @GetMapping("/chats/{id}")
  public ResponseEntity<List<MessageReadDto>> getAllMessages(@PathVariable Long id) {
    List<MessageReadDto> messages = messageService.findAllByChatId(id);
    return messages.isEmpty()
        ? noContent().build()
        : ok(messages);
  }

  @PostMapping("/chats")
  public ResponseEntity<ChatReadDto> createChat(@RequestBody ChatCreateDto chat) {
    ChatReadDto chatReadDto = chatService.create(chat);
    return Objects.isNull(chatReadDto)
        ? badRequest().build()
        : created(URI.create("/chats/" + chatReadDto.getId()))
            .body(chatReadDto);
  }

  @DeleteMapping("/chats/{id}")
  public ResponseEntity<?> deleteChat(@PathVariable Long id) {
    return chatService.delete(id)
        ? noContent().build()
        : notFound().build();
  }

}
