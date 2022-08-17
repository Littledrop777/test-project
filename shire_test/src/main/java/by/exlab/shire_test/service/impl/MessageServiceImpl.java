package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.dao.MessageDao;
import by.exlab.shire_test.dto.MessageCreateDto;
import by.exlab.shire_test.dto.MessageReadDto;
import by.exlab.shire_test.exception.EntityCreateException;
import by.exlab.shire_test.mapper.MessageCreateMapper;
import by.exlab.shire_test.mapper.MessageReadMapper;
import by.exlab.shire_test.service.MessageService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

  private final MessageDao messageDao;
  private final MessageCreateMapper messageCreateMapper;
  private final MessageReadMapper messageReadMapper;

  @Override
  @Transactional
  public MessageReadDto create(MessageCreateDto messageCreateDto) {
    return Optional.of(messageCreateDto)
        .map(messageCreateMapper::map)
        .map(messageDao::save)
        .map(messageReadMapper::map)
        .orElseThrow(EntityCreateException::new);
  }

  @Override
  public List<MessageReadDto> findAllByChatId(Long chatId) {
    return messageDao.findAllByChatId(chatId).stream()
        .map(messageReadMapper::map)
        .toList();
  }
}
