package by.exlab.shire_test.dao;

import by.exlab.shire_test.entity.UserChat;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatDao extends JpaRepository<UserChat, Long> {

  Optional<UserChat> findByFirstUserIdAndSecondUserId(Long senderUserId, Long receiverUserId);
}
