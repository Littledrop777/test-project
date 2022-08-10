package by.exlab.shire_test.dao;

import by.exlab.shire_test.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDao extends JpaRepository<Chat, Long> {

}
