package by.exlab.shire_test.dto;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class ChatReadDto {

  Long id;
  AppUserReadDto firstUser;
  AppUserReadDto secondUser;
}
