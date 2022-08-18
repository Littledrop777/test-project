package by.exlab.shire_test.service;

import by.exlab.shire_test.dto.AppUserReadDto;
import java.util.Optional;

public interface AppUserService {

  Optional<AppUserReadDto> findById(Long userId);
}
