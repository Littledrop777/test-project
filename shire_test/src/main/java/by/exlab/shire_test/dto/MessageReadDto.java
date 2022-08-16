package by.exlab.shire_test.dto;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class MessageReadDto {

  Long id;
  Long chatId;
  AppUserReadDto sender;
  AppUserReadDto recipient;
  String content;
  LocalDateTime createTime;
}
