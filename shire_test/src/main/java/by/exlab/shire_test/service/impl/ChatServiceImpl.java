package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.dao.ChatDao;
import by.exlab.shire_test.dto.ChatCreateDto;
import by.exlab.shire_test.dto.ChatReadDto;
import by.exlab.shire_test.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatDao chatDao;

  @Override
  public ChatReadDto create(ChatCreateDto chat) {
    return null;
  }

  @Override
  public boolean delete(Long id) {
    return chatDao.findById(id)
        .map(chat -> {
          chatDao.delete(chat);
          chatDao.flush();
          return true;
        })
        .orElse(false);
  }
}
