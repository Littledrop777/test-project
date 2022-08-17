package by.exlab.shire_test.dao;

import by.exlab.shire_test.entity.Chat;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDao extends JpaRepository<Chat, Long> {

  Optional<Chat> findByFirstUserIdAndSecondUserId(Long senderUserId, Long receiverUserId);
}
