package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.dao.ChatDao;
import by.exlab.shire_test.dao.UserChatDao;
import by.exlab.shire_test.entity.ChatMessage;
import by.exlab.shire_test.entity.UserChat;
import by.exlab.shire_test.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatDao chatDao;
  private final UserChatDao userChatDao;

  @Override
  public UserChat findByFirstUserIdAndSecondUserId(Long senderUserId, Long receiverUserId) {
    return userChatDao.findByFirstUserIdAndSecondUserId(senderUserId, receiverUserId).orElse(null);
  }

  @Override
  public ChatMessage save(ChatMessage chatMessage) {
    return chatDao.save(chatMessage);
  }

  @Override
  public List<ChatMessage> findAllByUserChatId(Long userChatId) {
    return chatDao.findAllByUserChatId(userChatId);
  }
}
