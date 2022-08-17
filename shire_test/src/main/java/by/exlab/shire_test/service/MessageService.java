package by.exlab.shire_test.service;

import by.exlab.shire_test.dto.MessageCreateDto;
import by.exlab.shire_test.dto.MessageReadDto;
import java.util.List;
import java.util.Optional;

public interface MessageService {

  MessageReadDto create(MessageCreateDto messageCreateDto);

  List<MessageReadDto> findAllByChatId(Long chatId);
}



