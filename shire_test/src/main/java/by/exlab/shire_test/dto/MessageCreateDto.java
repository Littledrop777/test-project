package by.exlab.shire_test.dto;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class MessageCreateDto {

  Long chatId;
  Long senderId;
  Long recipientId;
  String content;
  LocalDateTime createTime;
}
