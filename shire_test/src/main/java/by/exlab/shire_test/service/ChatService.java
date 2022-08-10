package by.exlab.shire_test.service;

import by.exlab.shire_test.entity.Chat;
import by.exlab.shire_test.entity.UserChat;

public interface ChatService {

  UserChat findByFirstUserIdAndSecondUserId(Long senderUserId, Long receiverUserId);

  Chat save(Chat chat);
}


