package by.exlab.shire_test.service;

import by.exlab.shire_test.entity.ChatMessage;
import by.exlab.shire_test.entity.UserChat;
import java.util.List;

public interface ChatService {

  UserChat findByFirstUserIdAndSecondUserId(Long senderUserId, Long receiverUserId);

  ChatMessage save(ChatMessage chatMessage);

  List<ChatMessage> findAllByUserChatId(Long userChatId);
}



