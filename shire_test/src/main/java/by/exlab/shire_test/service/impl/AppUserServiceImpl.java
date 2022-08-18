package by.exlab.shire_test.service.impl;

import by.exlab.shire_test.dao.AppUserDao;
import by.exlab.shire_test.dto.AppUserReadDto;
import by.exlab.shire_test.mapper.AppUserReadMapper;
import by.exlab.shire_test.service.AppUserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

  private final AppUserDao userDao;
  private final AppUserReadMapper appUserReadMapper;

  @Override
  public Optional<AppUserReadDto> findById(Long userId) {
    return userDao.findById(userId)
        .map(appUserReadMapper::map);
  }
}
