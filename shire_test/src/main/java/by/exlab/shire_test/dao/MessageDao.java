package by.exlab.shire_test.dao;

import by.exlab.shire_test.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Long> {

  List<Message> findAllByChatId(Long chatId);
}
