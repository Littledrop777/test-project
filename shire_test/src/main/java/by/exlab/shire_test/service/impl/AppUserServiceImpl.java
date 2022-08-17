package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.entity.AppUser;
import by.exlab.shire_test.service.AppUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AppUserServiceImpl implements AppUserService {

  @Override
  public AppUser findById(Long senderUserId) {
    return null;
  }
}
