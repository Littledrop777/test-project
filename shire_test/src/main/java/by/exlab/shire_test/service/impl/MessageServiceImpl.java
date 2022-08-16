package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.dao.ChatDao;
import by.exlab.shire_test.dao.UserChatDao;
import by.exlab.shire_test.dto.MessageCreateDto;
import by.exlab.shire_test.dto.MessageReadDto;
import by.exlab.shire_test.service.MessageService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

  private final ChatDao chatDao;
  private final UserChatDao userChatDao;

  @Override
  public MessageReadDto create(MessageCreateDto messageCreateDto) {
    return null;
  }

  @Override
  public List<MessageReadDto> findAllByChatId(Long chatId) {
    return null;
  }
}
