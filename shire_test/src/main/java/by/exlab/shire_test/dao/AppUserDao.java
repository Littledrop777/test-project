package by.exlab.shire_test.dao;

import by.exlab.shire_test.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDao extends JpaRepository<AppUser, Long> {


}
