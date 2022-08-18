package by.exlab.shire_test.mapper;

import by.exlab.shire_test.dto.AppUserReadDto;
import by.exlab.shire_test.dto.ChatReadDto;
import by.exlab.shire_test.entity.AppUser;
import by.exlab.shire_test.entity.Chat;
import by.exlab.shire_test.exception.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatReadMapper implements Mapper<Chat, ChatReadDto> {

  private final AppUserReadMapper userMapper;

  @Override
  public ChatReadDto map(Chat object) {
    AppUserReadDto firstUser = getAppUserReadDto(object.getFirstUser());
    AppUserReadDto secondUser = getAppUserReadDto(object.getSecondUser());
    return new ChatReadDto(
        object.getId(),
        firstUser,
        secondUser
    );
  }

  private AppUserReadDto getAppUserReadDto(AppUser user) {
    return Optional.of(user)
        .map(userMapper::map)
        .orElseThrow(EntityNotFoundException::new);
  }
}
