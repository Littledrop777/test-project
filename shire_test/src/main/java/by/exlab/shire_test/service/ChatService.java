package by.exlab.shire_test.service;

import by.exlab.shire_test.dto.ChatCreateDto;
import by.exlab.shire_test.dto.ChatReadDto;

public interface ChatService {

  ChatReadDto create(ChatCreateDto chat);

  boolean delete(Long id);
}
