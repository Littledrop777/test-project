package by.exlab.shire_test.mapper;

import by.exlab.shire_test.dto.AppUserReadDto;
import by.exlab.shire_test.dto.MessageReadDto;
import by.exlab.shire_test.entity.AppUser;
import by.exlab.shire_test.entity.Message;
import by.exlab.shire_test.exception.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageReadMapper implements Mapper<Message, MessageReadDto> {

  private final AppUserReadMapper userMapper;

  @Override
  public MessageReadDto map(Message object) {
    AppUserReadDto sender = getAppUserReadDto(object.getRecipient());
    AppUserReadDto recipient = getAppUserReadDto(object.getRecipient());
    return new MessageReadDto(
        object.getId(),
        object.getChat().getId(),
        sender,
        recipient,
        object.getContent(),
        object.getCreateTime());
  }

  private AppUserReadDto getAppUserReadDto(AppUser user) {
    return Optional.of(user)
        .map(userMapper::map)
        .orElseThrow(EntityNotFoundException::new);
  }
}
