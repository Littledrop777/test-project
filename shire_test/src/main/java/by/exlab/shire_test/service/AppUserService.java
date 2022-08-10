package by.exlab.shire_test.service;

import by.exlab.shire_test.entity.AppUser;

public interface AppUserService {

  AppUser findById(Long userId);
}
