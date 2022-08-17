package by.exlab.shire_test.mapper;

import by.exlab.shire_test.dao.AppUserDao;
import by.exlab.shire_test.dao.ChatDao;
import by.exlab.shire_test.dto.MessageCreateDto;
import by.exlab.shire_test.entity.AppUser;
import by.exlab.shire_test.entity.Chat;
import by.exlab.shire_test.entity.Message;
import by.exlab.shire_test.exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageCreateMapper implements Mapper<MessageCreateDto, Message> {

  private final ChatDao chatDao;
  private final AppUserDao appUserDao;

  @Override
  public Message map(MessageCreateDto object) {
    return Message.builder()
        .chat(getChat(object.getChatId()))
        .sender(getAppUser(object.getSenderId()))
        .recipient(getAppUser(object.getRecipientId()))
        .content(object.getContent())
        .createTime(LocalDateTime.now()) // is it right to do it here?
        .build();
  }

  private AppUser getAppUser(Long id){
    return Optional.of(id)
        .flatMap(appUserDao::findById)
        .orElseThrow(EntityNotFoundException::new);
  }

  private Chat getChat(Long id){
    return Optional.of(id)
        .flatMap(chatDao::findById)
        .orElseThrow(EntityNotFoundException::new);
  }
}
