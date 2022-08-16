package by.exlab.shire_test.dao;

import by.exlab.shire_test.entity.ChatMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDao extends JpaRepository<ChatMessage, Long> {

  List<ChatMessage> findAllByUserChatId(Long userChatId);
}
