package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.entity.AppUser;
import by.exlab.shire_test.service.AppUserService;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

  @Override
  public AppUser findById(Long senderUserId) {
    return null;
  }
}
