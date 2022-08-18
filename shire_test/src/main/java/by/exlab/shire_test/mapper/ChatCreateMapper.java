package by.exlab.shire_test.mapper;

import by.exlab.shire_test.dao.AppUserDao;
import by.exlab.shire_test.dto.ChatCreateDto;
import by.exlab.shire_test.entity.AppUser;
import by.exlab.shire_test.entity.Chat;
import by.exlab.shire_test.exception.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatCreateMapper implements Mapper<ChatCreateDto, Chat> {

  private final AppUserDao appUserDao;

  @Override
  public Chat map(ChatCreateDto object) {
    AppUser firstUser = getAppUser(object.getFirstUserId());
    AppUser secondUser = getAppUser(object.getSecondUserId());
    return Chat.builder()
        .firstUser(firstUser)
        .secondUser(secondUser)
        .build();
  }

  private AppUser getAppUser(Long id) {
    return Optional.of(id)
        .flatMap(appUserDao::findById)
        .orElseThrow(EntityNotFoundException::new);
  }
}
