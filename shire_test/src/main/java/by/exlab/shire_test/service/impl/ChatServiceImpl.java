package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.dao.ChatDao;
import by.exlab.shire_test.dto.ChatCreateDto;
import by.exlab.shire_test.dto.ChatReadDto;
import by.exlab.shire_test.exception.EntityCreateException;
import by.exlab.shire_test.mapper.ChatCreateMapper;
import by.exlab.shire_test.mapper.ChatReadMapper;
import by.exlab.shire_test.service.ChatService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatDao chatDao;
  private final ChatCreateMapper chatCreateMapper;
  private final ChatReadMapper chatReadMapper;

  @Override
  public ChatReadDto create(ChatCreateDto chatCreateDto) {
    return Optional.of(chatCreateDto)
        .map(chatCreateMapper::map)
        .map(chatDao::save)
        .map(chatReadMapper::map)
        .orElseThrow(EntityCreateException::new);
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
